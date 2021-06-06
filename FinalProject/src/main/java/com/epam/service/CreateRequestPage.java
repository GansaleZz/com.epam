package com.epam.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateRequestPage implements Command {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CreateRequestPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
            logger.warn("User with login "+request.getSession().getAttribute("login")+" tried to got access to the page 'Create request'");
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 30);
            Date maxDay = calendar.getTime();
            Date today = Calendar.getInstance().getTime();
            request.setAttribute("maxDay", format.format(maxDay));
            request.setAttribute("today", format.format(today));
            try {
                request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTNEWREQUESTPAGE.getPath()).forward(request, response);
            } catch (ServletException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
