package com.epam.service;

import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.Request;
import com.epam.entity.Room;
import com.epam.entity.UserRole;
import com.epam.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowRequests implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        try {
            List<Request> finalList = new ArrayList<>();
            requestDao.findAllEntities().stream()
                    .filter(i -> i.getUser().getId() == (Integer) request.getSession().getAttribute("id"))
                    .forEach(i -> finalList.add(i));
            request.setAttribute("list", finalList);
            switch(UserRole.getRole((String) request.getSession().getAttribute("userRole"))){
                case CLIENT -> request.getServletContext().getRequestDispatcher("/usersView/client/requests.jsp").forward(request, response);
                case MODERATOR -> request.getServletContext().getRequestDispatcher("/usersView/moderator/requests.jsp").forward(request, response);
                case ADMIN -> request.getServletContext().getRequestDispatcher("/usersView/admin/requests.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
