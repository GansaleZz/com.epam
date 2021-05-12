package com.epam.service;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogIn implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("password") != null && request.getParameter("login") != null) {
            String pass = request.getParameter("password");
            UserDaoImpl userDao = new UserDaoImpl();
            UserCriteria login = new UserCriteria();
            login.setLogin(request.getParameter("login"));
            try {
                if (userDao.findUserByCriteria(login).isPresent() && userDao.findUserByCriteria(login).get().getPassword().equals(pass)) {
                    if(userDao.findUserByCriteria(login).get().getStatus().equals(UserStatus.BANNED)){
                        response.sendRedirect(ServletDestination.BANPAGE.getPath());
                    }else {
                        HttpSession session = request.getSession();
                        String userRole = String.valueOf(userDao.findUserByCriteria(login).get().getUserRole());
                        String userStatus = String.valueOf(userDao.findUserByCriteria(login).get().getStatus());
                        session.setAttribute("login", login.getLogin());
                        session.setAttribute("password", pass);
                        session.setAttribute("userRole", userRole);
                        session.setAttribute("userStatus",userStatus);
                        response.sendRedirect(ServletDestination.ADMINHOMEPAGE.getPath());
                    }
                } else {
                    response.sendRedirect(ServletDestination.LOGINERROR.getPath());
                }
            } catch (DaoException | IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.sendRedirect(ServletDestination.LOGINPAGE.getPath());
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
