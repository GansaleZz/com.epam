package com.epam.service;

import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;
import com.epam.util.Cache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateRequest implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(UserRole.getRole((String) request.getSession().getAttribute("userRole")).equals(UserRole.CLIENT)){
            response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWHOME");
        }else {
            if (request.getParameter("submit").equals("Deny")) {
                RequestDaoImpl requestDao = new RequestDaoImpl();
                try {
                    Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    req.setRequestStatus(RequestStatus.DENIED);
                    requestDao.update(req);
                    response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWREQUESTS");
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }else{
                if(request.getParameter("submit").equals("Approve")) {
                    try {
                        List<Room> list = new ArrayList<>();
                        Cache cache = Cache.getInstance();
                        RoomDaoImpl roomDao = new RoomDaoImpl();
                        RequestDaoImpl requestDao = new RequestDaoImpl();
                        Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                        Date start = req.getStart();
                        Date end = req.getEnd();
                        requestDao.findAllEntities().stream()
                                .filter(i -> i.getRequestStatus().equals(RequestStatus.ACCEPTED))
                                .forEach(i -> cache.addRequest(i));
                        roomDao.findAllEntities().stream()
                                .filter(i -> cache.isRoomEngaged(start, end, i) &&
                                        i.getRoomClass().equals(req.getRoomClass()) &&
                                        i.getNumberOfSeats() == req.getNumberOfSeats() &&
                                        i.getRoomStatus() != RoomStatus.ENGAGED)
                                .forEach(i -> list.add(i));
                        request.setAttribute("id",req.getId());
                        request.setAttribute("rooms", list);
                        switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                            case MODERATOR -> request.getServletContext().getRequestDispatcher("/usersView/moderator/approveRequest.jsp").forward(request, response);
                            case ADMIN -> request.getServletContext().getRequestDispatcher("/usersView/admin/approveRequest.jsp").forward(request, response);
                        }
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                }else{
                    try{
                        RequestDaoImpl requestDao = new RequestDaoImpl();
                        RoomDaoImpl roomDao = new RoomDaoImpl();
                        Cache cache = Cache.getInstance();
                        Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("reqId"))).get();
                        req.setRequestStatus(RequestStatus.ACCEPTED);
                        req.setRoom(roomDao.findEntityById(Integer.valueOf(request.getParameter("roomId"))).get());
                        cache.addRequest(req);
                        requestDao.update(req);
                        response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWREQUESTS");
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

