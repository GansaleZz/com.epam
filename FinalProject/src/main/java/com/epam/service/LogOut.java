package com.epam.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOut implements Command{
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LogOut.class);

    /**
     * User's logging out the website
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("User with login " + request.getParameter("login") + "log out");
        request.getSession().invalidate();
        response.sendRedirect(link + CommandInstance.ACTSHOWAUTH);
    }
}
