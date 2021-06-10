package com.epam.controller.ShowPage;

import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowLogInError implements Command {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShowLogInError.class);

    /**
     * Forwarding user on 'log in error' page, if he entered wrong information
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getServletContext().getRequestDispatcher(ServletDestination.LOGINERROR.getPath()).forward(request,response);
        } catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}
