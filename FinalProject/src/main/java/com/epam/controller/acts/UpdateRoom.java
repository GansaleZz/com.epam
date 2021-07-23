package com.epam.controller.acts;

import com.epam.controller.ServletDestination;
import com.epam.criteria.impl.RoomCriteria;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;


public class UpdateRoom implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UpdateRoom.class);

    /**
     * Realisation of updating information of room or deleting this one
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (UserRole.getRole((String) request.getSession().getAttribute("userRole")).equals(UserRole.CLIENT)) {
            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
            LOGGER.warn("Client with login " + request.getSession().getAttribute("login") + " tried to got access to the page 'Update room'");
        } else {
            RoomDaoImpl roomDao = new RoomDaoImpl();
            ResourceBundle bundle = ResourceBundle.getBundle("language_" + request.getSession().getAttribute("locale"));
            try {
                if (new String(request.getParameter("submit").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).equals(bundle.getString("delete"))) {
                    LOGGER.info("User with login "+request.getSession().getAttribute("login") + " deleted room with number" + roomDao.findEntityById(Integer.parseInt(request.getParameter("id"))));
                    roomDao.delete(Integer.parseInt(request.getParameter("id")));
                } else {
                    Room room = roomDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    Room oldRoom = new RoomCriteria.Builder().newBuilder()
                            .withRoomClass(room.getRoomClass())
                            .withRoomStatus(room.getRoomStatus())
                            .withRoomNumber(room.getRoomNumber())
                            .withPrice(room.getPrice())
                            .withId(room.getId())
                            .withNumberOfSeats(room.getNumberOfSeats())
                            .build();
                    room.setPrice(Integer.parseInt(request.getParameter("price")));
                    room.setNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")));
                    room.setRoomStatus(RoomStatus.valueOf(request.getParameter("status")));
                    room.setRoomClass(RoomClass.valueOf(request.getParameter("class")));
                    room.setRoomNumber(Integer.parseInt(request.getParameter("roomNumber")));
                    if(oldRoom.getRoomNumber()!= room.getRoomNumber() && roomDao.findAllEntities().stream().anyMatch(i -> i.getRoomNumber() == room.getRoomNumber())){
                        request.getServletContext().getRequestDispatcher(ServletDestination.BADROOMNUMBERPAGE.getPath()).forward(request,response);
                    }else {
                        LOGGER.info("User with login " + request.getSession().getAttribute("login") + " updated room from " + oldRoom + " to " + room);
                        roomDao.update(room);
                    }
                }
                response.sendRedirect(link + CommandInstance.ACT_SHOW_ROOMS);
            } catch (DaoException | ServletException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
