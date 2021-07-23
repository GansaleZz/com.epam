package com.epam.controller.showPage;

import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.entity.RequestStatus;
import com.epam.exceptions.DaoException;
import com.epam.controller.Command;
import com.epam.controller.CommandInstance;
import com.epam.controller.ServletDestination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShowCreateRequest implements Command {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ShowCreateRequest.class);

    /**
     * Method which use to show page of creating request.
     * In method sets in attributes 'maxDay', which need for limit max date of reserving room, and 'today',
     * which need to set in current date
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACT_SHOW_HOME);
            LOGGER.warn("User with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Create request'");
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            RequestDaoImpl requestDao = new RequestDaoImpl();
            calendar.add(Calendar.DATE, 30);
            Date maxDay = calendar.getTime();
            Date today = Calendar.getInstance().getTime();
            request.setAttribute("maxDay", format.format(maxDay));
            request.setAttribute("today", format.format(today));
            try {
                int requestsCount = (int) requestDao.findAllEntities().stream()
                        .filter(i -> i.getUser().getLogin().equals(request.getSession().getAttribute("login")) & !i.getRequestStatus().equals(RequestStatus.DENIED) & !i.getRequestStatus().equals(RequestStatus.CANCELLED))
                        .count();
                request.setAttribute("requestsCount",requestsCount);
                request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTNEWREQUESTPAGE.getPath()).forward(request, response);
            } catch (ServletException | DaoException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
