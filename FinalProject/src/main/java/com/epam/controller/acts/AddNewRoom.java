package com.epam.controller.acts;

import com.epam.controller.ServletDestination;
import com.epam.criteria.impl.RoomCriteria;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddNewRoom implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AddNewRoom.class);

    /**
     * Realisation of adding new room on db through taking
     * information from request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
            LOGGER.warn("Client with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Add new room'");
        }else {
            if (request.getParameter("price").trim().length() != 0 || request.getSession().getAttribute("userRole").equals("CLIENT")) {
                RoomDaoImpl roomDao = new RoomDaoImpl();
                try {
                    if(roomDao.findAllEntities().stream()
                            .anyMatch(i -> i.getRoomNumber() == Integer.parseInt(request.getParameter("roomNumber")))){
                        request.getServletContext().getRequestDispatcher(ServletDestination.BADROOMNUMBERPAGE.getPath()).forward(request,response);
                    }else{
                        Room room = new RoomCriteria.Builder()
                                .newBuilder()
                                .withPrice(Integer.parseInt(request.getParameter("price")))
                                .withRoomClass(RoomClass.valueOf(request.getParameter("roomClass")))
                                .withRoomStatus(RoomStatus.AVAILABLE)
                                .withNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")))
                                .withRoomNumber(Integer.parseInt(request.getParameter("roomNumber")))
                                .build();
                        LOGGER.info(room + " was created by user with login "+request.getSession().getAttribute("login"));
                        roomDao.create(room);
                    }
                } catch (DaoException | ServletException e) {
                    LOGGER.error(e.getMessage());
                }
            }
            response.sendRedirect(link + CommandInstance.ACT_SHOW_ROOMS);
        }
    }
}
