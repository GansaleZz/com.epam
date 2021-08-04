package com.epam.controller.acts;

import com.epam.controller.showPage.ShowVerifyPage;
import com.epam.criteria.impl.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.AccountStatus;
import com.epam.entity.User;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class LogIn implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LogIn.class);

    /**
     * User's logging in the website
     */
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
                        LOGGER.warn("Banned user with login" + user.getLogin()+" trying to sign in");
                        response.sendRedirect(link + CommandInstance.ACT_SHOW_BAN);
                    }else {
                        if(user.getAccountStatus().equals(AccountStatus.NOTACTIVATED)){
                            request.getSession(true).setAttribute("verify",true);
                            request.getSession(true).setAttribute("verifyTry", false);
                            request.getSession().setAttribute("login", user.getLogin());
                            response.sendRedirect(link + CommandInstance.ACT_SHOW_VERIFY_PAGE);
                        }else {
                            HttpSession session = request.getSession(true);
                            String userRole = String.valueOf(user.getUserRole());
                            String userStatus = String.valueOf(user.getStatus());
                            ResourceBundle bundle = ResourceBundle.getBundle("language_en");
                            session.setAttribute("login", user.getLogin());
                            session.setAttribute("password", pass);
                            session.setAttribute("userRole", userRole);
                            session.setAttribute("userStatus", userStatus);
                            session.setAttribute("id", user.getId());
                            session.setAttribute("locale", "en");
                            session.setAttribute("bundle", bundle);
                            LOGGER.info("User with login " + user.getLogin() + " signed in");
                            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
                        }
                    }
                } else {
                    response.sendRedirect(link + CommandInstance.ACT_SHOW_LOGIN_ERROR);
                }
            } catch (DaoException | IOException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            try {
                response.sendRedirect(link + CommandInstance.ACT_SHOW_LOGIN_ERROR);
            }catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
