package com.epam.service;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RealiseDeposite implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
        }else{
            try {
                if(request.getParameter("card").length()!=0) {
                    UserDaoImpl userDao = new UserDaoImpl();

                        User user = userDao.findEntityById((Integer) request.getSession().getAttribute("id")).get();
                        user.setBalance(user.getBalance() + Integer.parseInt(request.getParameter("balance")));
                        userDao.update(user);

                }
            } catch (DaoException e) {
                e.printStackTrace();
            }finally {
                response.sendRedirect(link + CommandInstance.ACTSHOWPROFILE);
            }
        }
    }
}
