package com.epam.service;

import com.epam.db.dao.impl.PaymentDaoImpl;
import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowRequests implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        try {
            List<Request> list = new ArrayList<>();
            if(UserRole.getRole((String) request.getSession().getAttribute("userRole")) == UserRole.CLIENT) {
                PaymentDaoImpl paymentDao = new PaymentDaoImpl();
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
                                list.add(i);
                            } catch (DaoException e) {
                                e.printStackTrace();
                            }
                        });
                request.setAttribute("list", list);
                request.getServletContext().getRequestDispatcher("/usersView/client/requests.jsp").forward(request, response);
            }else {
                requestDao.findAllEntities().stream()
                        .filter(i -> i.getRequestStatus() == RequestStatus.INPROGRESS)
                        .forEach(i -> list.add(i));
                request.setAttribute("list", list);
                switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                    case MODERATOR -> request.getServletContext().getRequestDispatcher("/usersView/moderator/requests.jsp").forward(request, response);
                    case ADMIN -> request.getServletContext().getRequestDispatcher("/usersView/admin/requests.jsp").forward(request, response);
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
