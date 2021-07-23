package com.epam.controller.acts;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class UpdateProfile implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UpdateProfile.class);

    /**
     * Realisation of changing information of user by changing it on profile
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            request.getSession().setAttribute("locale",request.getParameter("locale"));
            ResourceBundle bundle = ResourceBundle.getBundle("language_"+request.getParameter("locale"));
            request.getSession().setAttribute("bundle",bundle);
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.findEntityById(Integer.valueOf(request.getParameter("id"))).get();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            userDao.update(user);
            response.sendRedirect(link + CommandInstance.ACT_SHOW_PROFILE);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
