package com.epam.controller.acts;

import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.criteria.impl.UserCriteria;
import com.epam.db.dao.UserDao;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.AccountStatus;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyAccount implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(VerifyAccount.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        boolean isNumeric = code.chars().allMatch( Character::isDigit );
        try {
            if(isNumeric){
                UserDao userDao = new UserDaoImpl();
                UserCriteria userCriteria = new UserCriteria();
                userCriteria.setLogin((String) request.getSession().getAttribute("login"));
                User user = userDao.findUserByCriteria(userCriteria).get();
                if(user.getVerifyCode() == Integer.parseInt(request.getParameter("code"))){
                    user.setAccountStatus(AccountStatus.ACTIVATED);
                    userDao.update(user);
                    request.getSession().invalidate();
                    response.sendRedirect(link + CommandInstance.ACT_SHOW_SIGNUP_SUCC);
                }else{
                    request.getSession(true).setAttribute("verify",true);
                    request.getSession(true).setAttribute("verifyTry", true);
                    response.sendRedirect(link + CommandInstance.ACT_SHOW_VERIFY_PAGE);
                }
            }else{
                request.getSession(true).setAttribute("verify",true);
                request.getSession(true).setAttribute("verifyTry", true);
                response.sendRedirect(link + CommandInstance.ACT_SHOW_VERIFY_PAGE);
            }
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
