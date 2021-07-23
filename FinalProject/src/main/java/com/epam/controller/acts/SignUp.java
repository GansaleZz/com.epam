package com.epam.controller.acts;

import com.epam.criteria.impl.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SignUp.class);

    /**
     * User's signing up the website
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("password") != null && request.getParameter("login") != null && request.getParameter("email") != null) {
            String password = request.getParameter("password");
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            if (login.trim().length() != 0 && password.trim().length() != 0 && name.trim().length() != 0 && email.trim().length() != 0) {
                UserDaoImpl userDao = new UserDaoImpl();
                User user = new UserCriteria.Builder().newBuilder()
                        .withLogin(login)
                        .withPassword(password)
                        .withName(name)
                        .withEmail(email)
                        .build();
                try {
                    if (userDao.create(user)) {
                        LOGGER.info("User with login "+user.getLogin()+" was signed up");
                        response.sendRedirect(link + CommandInstance.ACT_SHOW_SIGNUP_SUCC);
                    } else {
                        response.sendRedirect(link + CommandInstance.ACT_SHOW_SIGNUP_ERROR);
                    }
                } catch (DaoException | IOException e) {
                    LOGGER.error(e.getMessage());
                }
            } else {
                response.sendRedirect(link + CommandInstance.ACT_SHOW_SIGNUP_ERROR);
            }
        }else {
            response.sendRedirect(link + CommandInstance.ACT_SHOW_SIGNUP_ERROR);
        }
    }
}
