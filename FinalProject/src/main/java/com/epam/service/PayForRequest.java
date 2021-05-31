package com.epam.service;

import com.epam.criteria.PaymentCriteria;
import com.epam.db.dao.impl.PaymentDaoImpl;
import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.exceptions.DaoException;
import com.epam.util.Cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class PayForRequest implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWHOME");
        }else {
            if(request.getParameter("submit").equals("Cancel")){
                RequestDaoImpl requestDao = new RequestDaoImpl();
                try {
                    Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    Calendar calendar = Calendar.getInstance();
                    Payment payment = new PaymentCriteria.Builder()
                            .newBuilder()
                            .withId(req.getId())
                            .withPaymentStatus(PaymentStatus.CANCELLED)
                            .withAmount((int)(req.getEnd().getTime()-req.getStart().getTime())*req.getRoom().getPrice()/ (1000*60*60*24))
                            .withDate(calendar.getTime())
                            .build();
                    PaymentDaoImpl paymentDao = new PaymentDaoImpl();
                    paymentDao.create(payment);
                    req.setRequestStatus(RequestStatus.CANCELLED);
                    req.setPayment(payment);
                    requestDao.update(req);
                    Cache cache = Cache.getInstance();
                    cache.removeRequest(req.getId());
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }else{
                RequestDaoImpl requestDao = new RequestDaoImpl();
                try {
                    Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    int price = (int)(req.getEnd().getTime()-req.getStart().getTime())/ (1000*60*60*24);
                    if(req.getUser().getBalance() >= price*req.getRoom().getPrice()) {
                        Calendar calendar =Calendar.getInstance();
                        UserDaoImpl userDao = new UserDaoImpl();
                        Payment payment = new PaymentCriteria.Builder()
                                .newBuilder()
                                .withId(req.getId())
                                .withPaymentStatus(PaymentStatus.PAID)
                                .withAmount(price)
                                .withDate(calendar.getTime())
                                .build();
                        req.getUser().setBalance(req.getUser().getBalance()-price*req.getRoom().getPrice());
                        req.setPayment(payment);
                        userDao.update(req.getUser());
                        requestDao.update(req);
                    }
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWREQUESTS");
        }
    }
}
