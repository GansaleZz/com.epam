package com.epam.service;

import com.epam.criteria.RequestCriteria;
import com.epam.criteria.RoomCriteria;
import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNewRequest implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
            RequestDaoImpl requestDao = new RequestDaoImpl();
            UserDaoImpl userDao = new UserDaoImpl();
        try {
            User user = userDao.findEntityById((Integer) request.getSession().getAttribute("id")).get();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(request.getParameter("start"));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,Integer.valueOf(request.getParameter("end")));
            Date end = calendar.getTime();
            Request newRequest = new RequestCriteria.Builder()
                    .newBuilder()
                    .withRequestStatus(RequestStatus.INPROGRESS)
                    .withRoomClass(RoomClass.valueOf(request.getParameter("class")))
                    .withStart(start)
                    .withEnd(end)
                    .withUser(user)
                    .withNumberOfSeats(Integer.valueOf(request.getParameter("numberOfSeats")))
                    .build();
            requestDao.create(newRequest);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
            response.sendRedirect("/usersView/client/home/home.jsp");
    }
}
