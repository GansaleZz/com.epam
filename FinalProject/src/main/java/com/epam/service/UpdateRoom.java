package com.epam.service;

import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class UpdateRoom implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(UserRole.getRole((String) request.getSession().getAttribute("userRole")).equals(UserRole.CLIENT)){
            response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWHOME");
        }else{
            RoomDaoImpl roomDao = new RoomDaoImpl();
            ResourceBundle bundle = ResourceBundle.getBundle("language_"+request.getSession().getAttribute("locale"));
            if(new String(request.getParameter("submit").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).equals(bundle.getString("delete"))){
                try {
                    roomDao.delete(Integer.valueOf(request.getParameter("id")));
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Room room = roomDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                    room.setPrice(Integer.valueOf(request.getParameter("price")));
                    room.setNumberOfSeats(Integer.valueOf(request.getParameter("numberOfSeats")));
                    room.setRoomStatus(RoomStatus.valueOf(request.getParameter("status")));
                    room.setRoomClass(RoomClass.valueOf(request.getParameter("class")));
                    room.setRoomNumber(Integer.valueOf(request.getParameter("roomNumber")));
                    roomDao.update(room);
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWROOMS");
        }
    }
}
