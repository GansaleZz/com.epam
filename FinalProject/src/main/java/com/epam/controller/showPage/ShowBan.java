package com.epam.controller.showPage;

import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowBan implements Command {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShowBan.class);

    /**
     * Forwarding user that was banned on 'ban' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getServletContext().getRequestDispatcher(ServletDestination.BANPAGE.getPath()).forward(request,response);
        } catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}
