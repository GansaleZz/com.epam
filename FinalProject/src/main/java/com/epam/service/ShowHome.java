package com.epam.service;

import com.epam.entity.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowHome implements Command{

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
            e.printStackTrace();
        }
    }
}
