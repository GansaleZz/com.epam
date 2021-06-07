package com.epam.service;

import com.epam.entity.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewRoomPage implements Command{
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewRoomPage.class);

    /**
     * Forwarding admin/client on 'New room' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
            logger.warn("Client with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'New room'");
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
