package com.epam.controller.showPage;

import com.epam.entity.UserRole;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewRoomPage implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(NewRoomPage.class);

    /**
     * Forwarding admin/client on 'New room' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
            LOGGER.warn("Client with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'New room'");
        }else{
            try {
                switch (UserRole.getRole((String) request.getSession().getAttribute("userRole"))) {
                    case ADMIN -> request.getServletContext().getRequestDispatcher(ServletDestination.ADMINNEWROOMPAGE.getPath()).forward(request, response);
                    case MODERATOR -> request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORNEWROOMPAGE.getPath()).forward(request, response);
                }
            }catch (ServletException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
