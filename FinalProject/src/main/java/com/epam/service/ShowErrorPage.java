package com.epam.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowErrorPage implements Command{
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ShowErrorPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            request.getServletContext().getRequestDispatcher(ServletDestination.ERROPAGE.getPath()).forward(request, response);
        }catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}
