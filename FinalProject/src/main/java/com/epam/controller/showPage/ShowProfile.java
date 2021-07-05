package com.epam.controller.showPage;

import com.epam.criteria.impl.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowProfile implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowProfile.class);

    /**
     * Forwarding user on 'profile' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            UserCriteria userCriteria = new UserCriteria();
            userCriteria.setLogin((String) request.getSession().getAttribute("login"));
            User user = userDao.findUserByCriteria(userCriteria).get();
            user.setBalance(userDao.findBalanceById(user.getId()));
            UserRole userRole = UserRole.getRole((String) request.getSession().getAttribute("userRole"));
            request.setAttribute("user", user);
            request.setAttribute("locale",request.getSession().getAttribute("locale"));
            switch(userRole){
                case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMINPROFILEPAGE.getPath()).forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORPROFILEPAGE.getPath()).forward(request, response);
                case CLIENT -> request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTPROFILEPAGE.getPath()).forward(request, response);
            }
        } catch (DaoException | ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
