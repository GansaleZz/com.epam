package com.epam.controller.showPage;

import com.epam.controller.Command;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAuth implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowAuth.class);

    /**
     * Forwarding user on 'authentication' page
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getSession().setAttribute("loginBad", false);
            request.getSession().setAttribute("emailBad", false);
            request.getSession().setAttribute("passBad", false);
            request.getSession().setAttribute("loginSignUp", null);
            request.getSession().setAttribute("passSignUp", null);
            request.getSession().setAttribute("nameSignUp", null);
            request.getSession().setAttribute("emailSignUp", null);
            request.getSession().setAttribute("loginSignIn", null);
            request.getSession().setAttribute("passSignIn", null);
            request.getServletContext().getRequestDispatcher(ServletDestination.AUTH_PAGE.getPath()).forward(request,response);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
