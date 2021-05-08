package com.epam.service;

import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowRooms implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<a href=\"http://localhost:8080/controller?command=LOGOUT\">Log Out</a>\n" +
                "\n" +
                "<a href=\"http://localhost:8080/usersView/admin/home/home.jsp\">Home</a>\n" +
                "\n" +
                "<a href=\"http://localhost:8080/controller?command=ACTSHOWROOMS\">Rooms</a>");
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            printWriter.println("<a href=\"http://localhost:8080/controller?command=ACTADDROOM\">Add new room</a>\n");
        }
        RoomDaoImpl roomDao = new RoomDaoImpl();
        try {
            List<Room> list = roomDao.findAllEntities();
            list.stream()
                    .forEach(i -> printWriter.println("<p> "+i+" </p>"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
