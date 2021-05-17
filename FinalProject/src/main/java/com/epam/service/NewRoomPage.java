package com.epam.service;

import com.epam.entity.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewRoomPage implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(ServletDestination.CLIENTHOMEPAGE.getPath());
        }else{
            try {
                switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                    case ADMIN -> request.getServletContext().getRequestDispatcher("/usersView/admin/newRoom.jsp").forward(request, response);
                    case MODERATOR -> request.getServletContext().getRequestDispatcher("/usersView/moderator/newRoom.jsp").forward(request, response);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
