package com.epam.service;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Changing users 'Role' and 'Status'
public class ChangeUsersRS implements Command{
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ChangeUsersRS.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
            logger.warn("Client with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Change users role and status'");
        }else {
            UserDaoImpl userDao = new UserDaoImpl();
            try {
                User user = userDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
                logger.info("Users role was changed from "+ user.getUserRole()+" to "+request.getParameter("role")+" and status from " + user.getStatus() + " to " + request.getParameter("status") + " by user with login " + request.getSession().getAttribute("login"));
                user.setStatus(UserStatus.getStatus(request.getParameter("status")));
                user.setUserRole(UserRole.getRole(request.getParameter("role")));
                userDao.update(user);
            } catch (DaoException e) {
                logger.error(e.getMessage());
            }
            response.sendRedirect(link + CommandInstance.ACTSHOWUSERS);
        }
    }
}
