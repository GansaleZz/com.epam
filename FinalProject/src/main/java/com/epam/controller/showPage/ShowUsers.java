package com.epam.controller.showPage;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowUsers implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowUsers.class);

    /**
     * Forwarding admin/moderator on 'users' page. Admin has opportunity to change user's role/status,
     * moderator can only see list of users
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
            LOGGER.warn("Client with login "+request.getSession().getAttribute("login")+" tried to get access to the page 'Show users'");
        }else {
            UserDaoImpl userDao = new UserDaoImpl();
            List<User> list = new ArrayList<>();
            try {
                list = userDao.findAllEntities();
            } catch (DaoException e) {
                LOGGER.error(e.getMessage());
            }
            request.setAttribute("list", list);
            try {
                if (request.getSession().getAttribute("userRole").equals("ADMIN")) {
                    request.getServletContext().getRequestDispatcher(ServletDestination.ADMIN_USERS_PAGE.getPath()).forward(request, response);
                } else {
                    request.getServletContext().getRequestDispatcher(ServletDestination.MODERATOR_USERS_PAGE.getPath()).forward(request, response);
                }
            } catch (ServletException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
