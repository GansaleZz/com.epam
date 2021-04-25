package com.epam.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LogIn")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        if(name.equals("abs") && pass.equals("asd")){
            HttpSession session = request.getSession();
            session.setAttribute("name",name);
            response.sendRedirect("home/home.jsp");
        }else{
            out.println("Wrong username or password!");
        }
    }
}
