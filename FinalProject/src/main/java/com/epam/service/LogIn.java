package com.epam.service;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogIn implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String pass = request.getParameter("password");
        UserDaoImpl userDao = new UserDaoImpl();
        UserCriteria login = new UserCriteria();
        login.setLogin(request.getParameter("login"));
        try {
            if(userDao.findUserByCriteria(login).isPresent() && userDao.findUserByCriteria(login).get().getPassword().equals(pass)){
                    HttpSession session = request.getSession();
                    String userRole = String.valueOf(userDao.findUserByCriteria(login).get().getRole());
                    session.setAttribute("login",login.getLogin());
                    session.setAttribute("password",pass);
                    session.setAttribute("userRole",userRole);
                    response.sendRedirect("home/home.jsp");
            }else{
                response.sendRedirect("logIn/logInError.jsp");
            }
        } catch (DaoException | IOException e) {
            e.printStackTrace();
        }
    }
}
