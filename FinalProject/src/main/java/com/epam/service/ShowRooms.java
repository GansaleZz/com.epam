package com.epam.service;

import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.Room;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowRooms implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
        List<Room> list = new ArrayList<>();
        try {
            list = roomDao.findAllEntities();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        request.setAttribute("list", list);
        try {
            switch(UserRole.getRole((String) request.getSession().getAttribute("userRole"))){
                case CLIENT -> request.getServletContext().getRequestDispatcher("/usersView/client/rooms.jsp").forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher("/usersView/moderator/rooms.jsp").forward(request, response);
                case ADMIN -> request.getServletContext().getRequestDispatcher("/usersView/admin/rooms.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
