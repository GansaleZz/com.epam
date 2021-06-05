package com.epam.service;

import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.Room;
import com.epam.entity.RoomStatus;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;
import com.epam.util.Cache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateRequest implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(UserRole.getRole((String) request.getSession().getAttribute("userRole")).equals(UserRole.CLIENT)){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
        }else {
            ResourceBundle bundle = ResourceBundle.getBundle("language_"+request.getSession().getAttribute("locale"));
            if (new String(request.getParameter("submit").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).equals(bundle.getString("deny"))) {
                RequestDaoImpl requestDao = new RequestDaoImpl();
                try {
                    Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    req.setRequestStatus(RequestStatus.DENIED);
                    requestDao.update(req);
                    response.sendRedirect(link + CommandInstance.ACTSHOWREQUESTS);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }else{
                if(new String(request.getParameter("submit").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).equals(bundle.getString("approve"))) {
                    try {
                        List<Room> list = new ArrayList<>();
                        Cache cache = Cache.getInstance();
                        cache.updateRequests();
                        RoomDaoImpl roomDao = new RoomDaoImpl();
                        RequestDaoImpl requestDao = new RequestDaoImpl();
                        Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                        Date start = req.getStart();
                        Date end = req.getEnd();
                        roomDao.findAllEntities().stream()
                                .filter(i -> cache.isRoomEngaged(start, end, i) &&
                                        i.getRoomClass().equals(req.getRoomClass()) &&
                                        i.getNumberOfSeats() == req.getNumberOfSeats() &&
                                        i.getRoomStatus() != RoomStatus.CLOSED)
                                .forEach(i -> list.add(i));
                        request.setAttribute("id",req.getId());
                        request.setAttribute("rooms", list);
                        switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                            case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORAPPROVEREQUESTPAGE.getPath()).forward(request, response);
                            case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMINAPPROVEREQUESTPAGE.getPath()).forward(request, response);
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
                        response.sendRedirect(link + CommandInstance.ACTSHOWREQUESTS);
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

