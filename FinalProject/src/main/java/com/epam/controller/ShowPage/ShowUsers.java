package com.epam.controller.ShowPage;

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
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShowUsers.class);

    /**
     * Forwarding admin/moderator on 'users' page. Admin has opportunity to change user's role/status,
     * moderator can only see list of users
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
            logger.warn("Client with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Show users'");
        }else {
            UserDaoImpl userDao = new UserDaoImpl();
            List<User> list = new ArrayList<>();
            try {
                list = userDao.findAllEntities();
            } catch (DaoException e) {
                e.printStackTrace();
            }
            request.setAttribute("list", list);
            try {
                if (request.getSession().getAttribute("userRole").equals("ADMIN")) {
                    request.getServletContext().getRequestDispatcher(ServletDestination.ADMINUSERSPAGE.getPath()).forward(request, response);
                } else {
                    request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORUSERSPAGE.getPath()).forward(request, response);
                }
            } catch (ServletException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
