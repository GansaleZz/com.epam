package com.epam.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/authentication/authPage.jsp");
    }

}
