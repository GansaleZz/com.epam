package com.epam.web;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        if(login.trim().length() != 0 && password.trim().length() != 0   && name.trim().length() != 0 ){
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User(login,password,name);
            try {
                if(userDao.create(user)){
                    response.sendRedirect("registration/signUpSucc.jsp");
                }else{
                    response.sendRedirect("registration/signUpError.jsp");
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }else{
            response.sendRedirect("registration/signUpError.jsp");
        }
    }
}
