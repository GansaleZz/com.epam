package com.epam.service;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowUsers implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
        }else {
            UserDaoImpl userDao = new UserDaoImpl();
            List<User> list = new ArrayList<>();
            try {
                list = userDao.findAllEntities();
            } catch (DaoException e) {
                e.printStackTrace();
            }
            request.setAttribute("list", list);
            try {
                if (request.getSession().getAttribute("userRole").equals("ADMIN")) {
                    request.getServletContext().getRequestDispatcher(ServletDestination.ADMINUSERSPAGE.getPath()).forward(request, response);
                } else {
                    request.getServletContext().getRequestDispatcher(ServletDestination.MODERATORUSERSPAGE.getPath()).forward(request, response);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
