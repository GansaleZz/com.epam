package com.epam.controller.ShowPage;

import com.epam.entity.UserRole;
import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowHome implements Command {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShowHome.class);

    /**
     * Forwarding user on 'home' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserRole userRole = UserRole.getRole((String) request.getSession().getAttribute("userRole"));
        try {
            switch (userRole) {
                case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMINHOMEPAGE.getPath()).forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORHOMEPAGE.getPath()).forward(request, response);
                case CLIENT -> request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTHOMEPAGE.getPath()).forward(request, response);
            }
        }catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}
