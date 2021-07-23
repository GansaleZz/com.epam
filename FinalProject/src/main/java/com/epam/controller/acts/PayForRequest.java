package com.epam.controller.acts;

import com.epam.criteria.impl.PaymentCriteria;
import com.epam.db.dao.impl.PaymentDaoImpl;
import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;
import com.epam.util.Cache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class PayForRequest implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PayForRequest.class);

    /**
     * Method that use to do act with request by client.
     * If client clicked 'Cancel', then status of request will be changed on 'Cancelled', else
     * if client has enough money to pay for request, then money will be debited from his balance, otherwise
     * client will be forwarded on the page 'bad balance'
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
            LOGGER.warn("User with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Pay for request'");
        }else {
            try {
                RequestDaoImpl requestDao = new RequestDaoImpl();
                Calendar calendar = Calendar.getInstance();
                Cache cache = Cache.getInstance();
                Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                int price = (int)((req.getEnd().getTime()-req.getStart().getTime())/ (1000*60*60*24));
                Payment payment = new PaymentCriteria.Builder()
                        .newBuilder()
                        .withId(req.getId())
                        .withAmount(price*req.getRoom().getPrice())
                        .withDate(calendar.getTime())
                        .build();
            if(request.getParameter("submit").equals("Cancel")){
                    PaymentDaoImpl paymentDao = new PaymentDaoImpl();
                    payment.setStatus(PaymentStatus.CANCELLED);
                    paymentDao.create(payment);
                    req.setRequestStatus(RequestStatus.CANCELLED);
                    req.setPayment(payment);
                    requestDao.update(req);
                    cache.removeRequest(req.getId());
                    response.sendRedirect(link + CommandInstance.ACT_SHOW_REQUESTS);
                    LOGGER.info("Client with login " + request.getSession().getAttribute("login") + " cancelled request with room number "+req.getRoom().getRoomNumber());
            }else{
                if(req.getUser().getBalance() >= payment.getAmount()) {
                    UserDaoImpl userDao = new UserDaoImpl();
                    PaymentDaoImpl paymentDao = new PaymentDaoImpl();
                    payment.setStatus(PaymentStatus.PAID);
                    paymentDao.create(payment);
                    req.setRequestStatus(RequestStatus.PAID);
                    req.getUser().setBalance(req.getUser().getBalance()-price*req.getRoom().getPrice());
                    req.setPayment(payment);
                    userDao.update(req.getUser());
                    requestDao.update(req);
                    cache.addRequest(req);
                    LOGGER.info("Client with login " + request.getSession().getAttribute("login") + " paid for request with room number "+req.getRoom().getRoomNumber());
                    response.sendRedirect(link + CommandInstance.ACT_SHOW_REQUESTS);
                }else{
                    request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTBADBALANCEPAGE.getPath()).forward(request,response);
                }
            }
        } catch (DaoException | ServletException e) {
            LOGGER.error(e.getMessage());
        }
        }
    }
}
