package com.epam.controller.showPage;

import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowVerifyPage implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowVerifyPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if(request.getSession(false).getAttribute("verify").equals(true)) {
                request.getSession(true).setAttribute("verify",false);
                request.setAttribute("verifyTry", request.getSession().getAttribute("verifyTry"));
                request.getServletContext().getRequestDispatcher(ServletDestination.VERIFY_PAGE.getPath()).forward(request, response);
            }else{
                response.sendRedirect(link + CommandInstance.ACT_SHOW_AUTH);
            }
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
