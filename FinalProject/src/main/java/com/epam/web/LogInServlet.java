package com.epam.web;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.exceptions.DaoException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LogIn")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pass = request.getParameter("password");
        UserDaoImpl userDao = new UserDaoImpl();
        UserCriteria login = new UserCriteria();
        login.setLogin(request.getParameter("login"));
        try {
            if(userDao.findUserByCriteria(login).isPresent()){
                if(userDao.findUserByCriteria(login).get().getPassword().equals(pass)){
                    HttpSession session = request.getSession();
                    String userRole = String.valueOf(userDao.findUserByCriteria(login).get().getRole());
                    session.setAttribute("login",login.getLogin());
                    session.setAttribute("password",pass);
                    session.setAttribute("userRole",userRole);
                    response.sendRedirect("home/home.jsp");
                }else{
                    response.sendRedirect("authentication/logInError.jsp");
                }
            }else{
                response.sendRedirect("authentication/logInError.jsp");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
