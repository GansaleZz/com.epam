package com.epam.service;

import com.epam.criteria.RoomCriteria;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewRoom implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(ServletDestination.CLIENTHOMEPAGE.getPath());
        }
        if(request.getParameter("price").trim().length()!=0 || request.getSession().getAttribute("userRole").equals("CLIENT")){
            RoomDaoImpl roomDao = new RoomDaoImpl();
            Room room = new RoomCriteria.Builder()
                    .newBuilder()
                    .withPrice(Integer.valueOf(request.getParameter("price")))
                    .withRoomClass(RoomClass.valueOf(request.getParameter("roomClass")))
                    .withRoomStatus(RoomStatus.AVAILABLE)
                    .withNumberOfSeats(Integer.valueOf(request.getParameter("numberOfSeats")))
                    .withRoomNumber(Integer.valueOf(request.getParameter("roomNumber")))
                    .build();
            try {
                roomDao.create(room);
            } catch (DaoException e) {
                e.printStackTrace();
            }
                response.sendRedirect("/controller?command=ACTSHOWROOMS");
        }else{
            response.sendRedirect("/controller?command=ACTSHOWROOMS");
        }
    }
}
