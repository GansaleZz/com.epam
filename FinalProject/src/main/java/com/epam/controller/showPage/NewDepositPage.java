package com.epam.controller.showPage;


import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewDepositPage implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(NewDepositPage.class);

    /**
     * Forwarding client on 'New deposit' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            LOGGER.warn("User with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'New deposit'");
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
        }else{
            try {
                request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTNEWDEPOSITPAGE.getPath()).forward(request, response);
            } catch (ServletException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
