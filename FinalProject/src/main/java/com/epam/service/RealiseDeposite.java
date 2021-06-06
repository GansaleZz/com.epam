package com.epam.service;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RealiseDeposite implements Command{
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RealiseDeposite.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
            logger.warn("User with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Realise deposit'");
        }else{
            try {
                if(request.getParameter("card").length()!=0) {
                    UserDaoImpl userDao = new UserDaoImpl();
                    User user = userDao.findEntityById((Integer) request.getSession().getAttribute("id")).get();
                    user.setBalance(user.getBalance() + Integer.parseInt(request.getParameter("balance")));
                    userDao.update(user);
                    logger.info("Client with login + "+user.getLogin()+" topped up the balance for " + request.getParameter("balance")+ " BYN");
                }
            } catch (DaoException e) {
                logger.error(e.getMessage());
            }finally {
                response.sendRedirect(link + CommandInstance.ACTSHOWPROFILE);
            }
        }
    }
}