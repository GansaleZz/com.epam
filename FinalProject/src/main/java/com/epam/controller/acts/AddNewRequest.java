package com.epam.controller.acts;

import com.epam.criteria.impl.RequestCriteria;
import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.RoomClass;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddNewRequest implements Command {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AddNewRequest.class);

    /**
     * Realisation of adding new request on db through taking
     * information from request
     * @param request parameter from which information is taken
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            logger.warn("Client with login " + request.getSession().getAttribute("login")+" tried to got access to the page 'Add new request'");
            response.sendRedirect(link+ CommandInstance.ACTSHOWHOME);
        }else {
            RequestDaoImpl requestDao = new RequestDaoImpl();
            UserDaoImpl userDao = new UserDaoImpl();
            try {
                User user = userDao.findEntityById((Integer) request.getSession().getAttribute("id")).get();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date start = format.parse(request.getParameter("start"));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(start);
                calendar.add(Calendar.DATE, Integer.parseInt(request.getParameter("end")));
                Date end = calendar.getTime();
                Request newRequest = new RequestCriteria.Builder()
                        .newBuilder()
                        .withRequestStatus(RequestStatus.INPROGRESS)
                        .withRoomClass(RoomClass.valueOf(request.getParameter("class")))
                        .withStart(start)
                        .withEnd(end)
                        .withUser(user)
                        .withNumberOfSeats(Integer.parseInt(request.getParameter("numberOfSeats")))
                        .build();
                requestDao.create(newRequest);
                logger.info(newRequest + " was created by user with login "+request.getSession().getAttribute("login"));
            } catch (ParseException | DaoException e) {
                logger.error(e.getMessage());
            }
            response.sendRedirect(link + CommandInstance.ACTSHOWREQUESTS);
        }
    }
}
