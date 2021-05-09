package com.epam.service;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserStatus implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            User user = userDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
            user.setStatus(UserStatus.getStatus(request.getParameter("status")));
            System.out.println(user);
            System.out.println(UserStatus.getStatus(request.getParameter("status")));
            System.out.println(userDao.update(user));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWUSERS");
    }
}
