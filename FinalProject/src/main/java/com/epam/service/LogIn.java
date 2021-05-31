package com.epam.service;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
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
            UserCriteria criteria = new UserCriteria();
            criteria.setLogin(request.getParameter("login"));
            try {

                if (userDao.findUserByCriteria(criteria).isPresent() && userDao.findUserByCriteria(criteria).get().getPassword().equals(pass)) {
                    User user = userDao.findUserByCriteria(criteria).get();
                    if(user.getStatus().equals(UserStatus.BANNED)){
                        response.sendRedirect(ServletDestination.BANPAGE.getPath());
                    }else {
                        HttpSession session = request.getSession();
                        String userRole = String.valueOf(user.getUserRole());
                        String userStatus = String.valueOf(user.getStatus());
                        session.setAttribute("login", user.getLogin());
                        session.setAttribute("password", pass);
                        session.setAttribute("userRole", userRole);
                        session.setAttribute("userStatus",userStatus);
                        session.setAttribute("id",user.getId());
                        response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWHOME");
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
