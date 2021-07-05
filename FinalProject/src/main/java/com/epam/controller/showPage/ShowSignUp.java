package com.epam.controller.showPage;

import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowSignUp implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowSignUp.class);

    /**
     * Forwarding user on 'sign up' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getServletContext().getRequestDispatcher(ServletDestination.SIGNUPPAGE.getPath()).forward(request,response);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
