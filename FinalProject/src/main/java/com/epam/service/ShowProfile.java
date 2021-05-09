package com.epam.service;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowProfile implements Command{
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
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            UserCriteria userCriteria = new UserCriteria();
            userCriteria.setLogin((String) request.getSession().getAttribute("login"));
            User user = userDao.findUserByCriteria(userCriteria).get();
            printWriter.println("<p>Login:"+user.getLogin()+"</p>" +
                    "<p>Name: "+user.getName()+"</p>"+
                    "<p>Email: "+user.getEmail()+"</p>"+
                    "<p>Role: "+user.getUserRole()+"</p>");
        }catch(NullPointerException | DaoException e){
            e.printStackTrace();
        }
    }
}
