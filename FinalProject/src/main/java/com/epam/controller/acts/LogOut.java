package com.epam.controller.acts;

import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOut implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LogOut.class);

    /**
     * User's logging out the website
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("User with login " + request.getParameter("login") + "log out");
        request.getSession().invalidate();
        response.sendRedirect(link + CommandInstance.ACT_SHOW_AUTH);
    }
}
