package com.epam.service;

import com.epam.db.dao.impl.PaymentDaoImpl;
import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.entity.Payment;
import com.epam.entity.Request;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowRequests implements Command{
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShowRequests.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        try {
            List<Request> list = new ArrayList<>();
            if(UserRole.getRole((String) request.getSession().getAttribute("userRole")) == UserRole.CLIENT) {
                PaymentDaoImpl paymentDao = new PaymentDaoImpl();
                List<Request> finalList = list;
                requestDao.findAllEntities().stream()
                        .filter(i -> i.getUser().getId() == (Integer) request.getSession().getAttribute("id"))
                        .forEach(i -> {
                            try {
                                Optional<Payment> payment =paymentDao.findAllEntities().stream()
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
                request.setAttribute("list", list);
                request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTREQEUSTSPAGE.getPath()).forward(request, response);
            }else {
                list = requestDao.findAllEntities();
                request.setAttribute("list", list);
                switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                    case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORREQUESTSPAGE.getPath()).forward(request, response);
                    case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMINREQUESTSPAGE.getPath()).forward(request, response);
                }
            }
        } catch (ServletException | DaoException e) {
            logger.error(e.getMessage());
        }
    }
}
