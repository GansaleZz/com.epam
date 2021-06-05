package com.epam.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowLogInError implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.getServletContext().getRequestDispatcher(ServletDestination.LOGINERROR.getPath()).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
