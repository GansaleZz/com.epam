package com.epam.service;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        if(login.trim().length() != 0 && password.trim().length() != 0   && name.trim().length() != 0 ){
            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User(login,password,name);
            try {
                if(userDao.create(user)){
                    response.sendRedirect("signUp/signUpSucc.jsp");
                }else{
                    response.sendRedirect("signUp/signUpError.jsp");
                }
            } catch (DaoException | IOException e) {
                e.printStackTrace();
            }
        }else{
            response.sendRedirect("signUp/signUpError.jsp");
        }
    }
}
