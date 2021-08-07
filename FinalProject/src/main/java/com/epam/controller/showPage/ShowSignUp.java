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
            request.getServletContext().getRequestDispatcher(ServletDestination.SIGNUP_PAGE.getPath()).forward(request,response);
            request.getSession().setAttribute("loginBad", false);
            request.getSession().setAttribute("emailBad", false);
            request.getSession().setAttribute("loginSignUp", null);
            request.getSession().setAttribute("nameSignUp", null);
            request.getSession().setAttribute("passSignUp", null);
            request.getSession().setAttribute("emailSignUp", null);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
