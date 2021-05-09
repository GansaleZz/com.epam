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
                "    <a href=\"http://localhost:8080/usersView/admin/home/home.jsp\">Home</a>\n" +
                "\n" +
                "    <a href=\"http://localhost:8080/controller?command=ACTSHOWROOMS\">Rooms</a>\n" +
                "\n" +
                "    <a href=\"http://localhost:8080/controller?command=ACTSHOWPROFILE\">Profile</a>\n" +
                "\n" +
                "    <a href=\"http://localhost:8080/controller?command=ACTCREATEREQUEST\">Create request</a>");
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            printWriter.println("<a href=\"http://localhost:8080/controller?command=ACTSHOWUSERS\">Users list</a>");
        }
        RoomDaoImpl roomDao = new RoomDaoImpl();
        try {
            List<Room> list = roomDao.findAllEntities();
            list.stream()
                    .forEach(i -> printWriter.println("<p><b> Id: </b>"+i.getId()+"<br>"+
                            "<b>Room status: </b>"+i.getRoomStatus()+"<br>"+
                            "<b>Room class:  </b>"+i.getRoomClass()+"<br>"+
                            "<b>Number of seats: </b>"+i.getNumberOfSeats()+"<br>"+
                            "<b>Price: </b>"+i.getPrice()+"<br>"+"</p>"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            printWriter.println("<a href=\"http://localhost:8080/controller?command=ACTADDROOM\">Add new room</a>\n");
            printWriter.println("<a href=\"http://localhost:8080/controller?command=ACTDELETEROOM\">Delete room</a>\n");
        }
    }
}
