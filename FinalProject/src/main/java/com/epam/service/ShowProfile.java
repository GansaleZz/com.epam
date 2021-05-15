package com.epam.service;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.UserDao;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowProfile implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            UserCriteria userCriteria = new UserCriteria();
            userCriteria.setLogin((String) request.getSession().getAttribute("login"));
            User user = userDao.findUserByCriteria(userCriteria).get();
            UserRole userRole = UserRole.getRole((String) request.getSession().getAttribute("userRole"));
            request.setAttribute("user", user);
            switch(userRole){
                case ADMIN -> request.getServletContext().getRequestDispatcher("/usersView/admin/profile.jsp").forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher("/usersView/moderator/profile.jsp").forward(request, response);
                case CLIENT -> request.getServletContext().getRequestDispatcher("/usersView/client/profile.jsp").forward(request, response);
            }
        } catch (DaoException | ServletException e) {
            e.printStackTrace();
        }
    }
}
