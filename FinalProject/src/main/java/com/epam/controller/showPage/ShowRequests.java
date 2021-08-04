package com.epam.controller.showPage;

import com.epam.db.dao.impl.PaymentDaoImpl;
import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.entity.Payment;
import com.epam.entity.Request;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.ServletDestination;
import com.epam.controller.acts.UpdateRequest;
import com.epam.util.Cache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowRequests implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowRequests.class);

    /**
     * Forwarding user on 'requests' page. For clients setting only their own requests, for moderators/admins -
     * all requests, with opportunity to answer them
     *
     * @see UpdateRequest
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        try {
            List<Request> list = new ArrayList<>();
            Cache cache = Cache.getInstance();
            cache.updateRequests();
            if(UserRole.getRole((String) request.getSession().getAttribute("userRole")).equals(UserRole.CLIENT)) {
                PaymentDaoImpl paymentDao = new PaymentDaoImpl();
                List<Request> finalList = list;
                requestDao.findAllEntities().stream()
                        .filter(i -> i.getUser().getId() == (Integer) request.getSession().getAttribute("id"))
                        .forEach(i -> {
                            try {
                                Optional<Payment> payment = paymentDao.findAllEntities().stream()
                                        .filter(j -> j.getId() == i.getId())
                                        .findAny();
                                if(payment.isPresent()){
                                    i.setPayment(payment.get());
                                }
                                finalList.add(i);
                            } catch (DaoException e) {
                                e.printStackTrace();
                            }
                        });
                request.setAttribute("list", finalList);
                request.getServletContext().getRequestDispatcher(ServletDestination.CLIENT_REQUESTS_PAGE.getPath()).forward(request, response);
            }else {
                list = requestDao.findAllEntities();
                request.setAttribute("list", list);
                switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                    case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATOR_REQUESTS_PAGE.getPath()).forward(request, response);
                    case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMIN_REQUESTS_PAGE.getPath()).forward(request, response);
                }
            }
        } catch (ServletException | DaoException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
