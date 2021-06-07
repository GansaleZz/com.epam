package com.epam.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowLogIn implements Command{
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShowLogIn.class);

    /**
     * Forwarding user on 'log in' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getServletContext().getRequestDispatcher(ServletDestination.LOGINPAGE.getPath()).forward(request,response);
        } catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}
