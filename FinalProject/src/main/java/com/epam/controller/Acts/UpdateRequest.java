package com.epam.controller.Acts;

import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.Room;
import com.epam.entity.RoomStatus;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;
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

public class UpdateRequest implements Command {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UpdateRequest.class);

    /**
     * Realisation of updating request's information depending on button which user clicked.
     * Admin or moderator can deny request of client, otherwise they can approve request
     * if suitable room exists. If admin/moderator clicked 'approve', they will be forwarded on page,
     * where they need to choose suitable room, if its exists
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(UserRole.getRole((String) request.getSession().getAttribute("userRole")).equals(UserRole.CLIENT)){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
            logger.warn("Client with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Update request'");
        }else {
            ResourceBundle bundle = ResourceBundle.getBundle("language_"+request.getSession().getAttribute("locale"));
            if (new String(request.getParameter("submit").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).equals(bundle.getString("deny"))) {
                RequestDaoImpl requestDao = new RequestDaoImpl();
                try {
                    Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    req.setRequestStatus(RequestStatus.DENIED);
                    logger.info("User with login " + request.getSession().getAttribute("login")+" denied request of user with login "+req.getUser().getLogin());
                    requestDao.update(req);
                    response.sendRedirect(link + CommandInstance.ACTSHOWREQUESTS);
                } catch (DaoException e) {
                    logger.error(e.getMessage());
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
                    } catch (ServletException | DaoException e) {
                        logger.error(e.getMessage());
                    }
                }else{
                    try{
                        RequestDaoImpl requestDao = new RequestDaoImpl();
                        RoomDaoImpl roomDao = new RoomDaoImpl();
                        Cache cache = Cache.getInstance();
                        Request req = requestDao.findEntityById(Integer.valueOf(request.getParameter("reqId"))).get();
                        req.setRequestStatus(RequestStatus.ACCEPTED);
                        req.setRoom(roomDao.findEntityById(Integer.valueOf(request.getParameter("roomId"))).get());
                        logger.info("User with login " + request.getSession().getAttribute("login")+" accepted request of user with login "+req.getUser().getLogin());
                        cache.addRequest(req);
                        requestDao.update(req);
                        response.sendRedirect(link + CommandInstance.ACTSHOWREQUESTS);
                    } catch (DaoException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
    }
}

