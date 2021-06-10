package com.epam.controller.Acts;

import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;


public class UpdateRoom implements Command {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UpdateRoom.class);

    /**
     * Realisation of updating information of room or deleting this one
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (UserRole.getRole((String) request.getSession().getAttribute("userRole")).equals(UserRole.CLIENT)) {
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
            logger.warn("Client with login " + request.getSession().getAttribute("login") + " tried to got access to the page 'Update room'");
        } else {
            RoomDaoImpl roomDao = new RoomDaoImpl();
            ResourceBundle bundle = ResourceBundle.getBundle("language_" + request.getSession().getAttribute("locale"));
            try {
                if (new String(request.getParameter("submit").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).equals(bundle.getString("delete"))) {
                    logger.info("User with login "+request.getSession().getAttribute("login") + " deleted room with number" + roomDao.findEntityById(Integer.parseInt(request.getParameter("id"))));
                    roomDao.delete(Integer.parseInt(request.getParameter("id")));
                } else {
                    Room room = roomDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    Room oldRoom = room;
                    room.setPrice(Integer.parseInt(request.getParameter("price")));
                    room.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));
                    room.setRoomStatus(RoomStatus.valueOf(request.getParameter("status")));
                    room.setRoomClass(RoomClass.valueOf(request.getParameter("class")));
                    room.setRoomNumber(Integer.parseInt(request.getParameter("roomNumber")));
                    logger.info("User with login "+request.getSession().getAttribute("login") + " updated room from "+oldRoom + " to "+room );
                    roomDao.update(room);
                }
                response.sendRedirect(link + CommandInstance.ACTSHOWROOMS);
            } catch (DaoException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
