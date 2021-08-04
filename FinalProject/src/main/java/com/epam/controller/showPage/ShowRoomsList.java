package com.epam.controller.showPage;

import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;
import com.epam.util.Cache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowRoomsList implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowRoomsList.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if(!request.getSession().getAttribute("userRole").equals("CLIENT")) {
                response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
                LOGGER.warn("User with login " + request.getSession().getAttribute("login") + " tried to get access to the page 'Show rooms list'");
            }else{
                RoomDaoImpl roomDao = new RoomDaoImpl();
                List<Room> list = new ArrayList<>();
                try {
                    list = roomDao.findAllEntities();
                } catch (DaoException e) {
                    LOGGER.error(e.getMessage());
                }
                Cache.getInstance().updateRequests();
                request.setAttribute("list", list);
                request.setAttribute("reservedRooms", Cache.getInstance().getActiveRequests());
                request.getServletContext().getRequestDispatcher(ServletDestination.CLIENT_ROOMS_LIST_PAGE.getPath()).forward(request, response);
            }
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
