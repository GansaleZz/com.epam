package com.epam.controller.showPage;

import com.epam.entity.UserRole;
import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowHome implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowHome.class);

    /**
     * Forwarding user on 'home' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserRole userRole = UserRole.getRole((String) request.getSession().getAttribute("userRole"));
        try {
            switch (userRole) {
                case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMIN_HOME_PAGE.getPath()).forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATOR_HOME_PAGE.getPath()).forward(request, response);
                case CLIENT -> request.getServletContext().getRequestDispatcher(ServletDestination.CLIENT_HOME_PAGE.getPath()).forward(request, response);
            }
        }catch (ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
