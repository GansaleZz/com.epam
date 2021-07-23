package com.epam.controller.showPage;

import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowRooms implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowRooms.class);

    /**
     * Forwarding user on 'rooms' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
        List<Room> list = new ArrayList<>();
        try {
            list = roomDao.findAllEntities();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
        }
        request.setAttribute("list", list);
        try {
            switch(UserRole.getRole((String) request.getSession().getAttribute("userRole"))){
                case CLIENT -> request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTROOMSPAGE.getPath()).forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORROOMSPAGE.getPath()).forward(request, response);
                case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMINROOMSPAGE.getPath()).forward(request, response);
            }
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
