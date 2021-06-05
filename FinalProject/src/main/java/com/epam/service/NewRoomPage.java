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
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
        }else{
            try {
                switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                    case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMINNEWROOMPAGE.getPath()).forward(request, response);
                    case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORNEWROOMPAGE.getPath()).forward(request, response);
                }
            }catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
