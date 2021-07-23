package com.epam.controller.acts;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RealiseDeposit implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RealiseDeposit.class);


    /**
     * Method which need to realise deposit of client
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
            LOGGER.warn("User with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Realise deposit'");
        }else{
            try {
                if(request.getParameter("card").length()!=0) {
                    UserDaoImpl userDao = new UserDaoImpl();
                    User user = userDao.findEntityById((Integer) request.getSession().getAttribute("id")).get();
                    user.setBalance(user.getBalance() + Integer.parseInt(request.getParameter("balance")));
                    userDao.update(user);
                    LOGGER.info("Client with login + "+user.getLogin()+" topped up the balance for " + request.getParameter("balance")+ " BYN");
                }
            } catch (DaoException e) {
                LOGGER.error(e.getMessage());
            }finally {
                response.sendRedirect(link + CommandInstance.ACT_SHOW_PROFILE);
            }
        }
    }
}
