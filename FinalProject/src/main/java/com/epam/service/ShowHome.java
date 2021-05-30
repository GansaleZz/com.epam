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
            switch(userRole){
                case ADMIN -> request.getServletContext().getRequestDispatcher("/usersView/admin/home/home.jsp").forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher("/usersView/moderator/home/home.jsp").forward(request, response);
                case CLIENT -> request.getServletContext().getRequestDispatcher("/usersView/client/home/home.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
