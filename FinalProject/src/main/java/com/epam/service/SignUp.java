package com.epam.service;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        if (login.trim().length() != 0 && password.trim().length() != 0 && name.trim().length() != 0) {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new UserCriteria.Builder().newBuilder()
                    .withLogin(login)
                    .withPassword(password)
                    .withName(name)
                    .build();

            try {
                if (userDao.create(user)) {
                    response.sendRedirect(ServletDestination.SIGNUPSUCC.getPath());
                } else {
                    response.sendRedirect(ServletDestination.SIGNUPERROR.getPath());
                }
            } catch (DaoException | IOException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect(ServletDestination.SIGNUPERROR.getPath());
        }
    }
}
