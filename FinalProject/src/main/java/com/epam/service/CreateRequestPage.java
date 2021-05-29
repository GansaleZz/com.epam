package com.epam.service;

import com.epam.entity.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateRequestPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect("http://localhost:8080/controller?command=ACTSHOWHOME");
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 30);
            Date maxDay = calendar.getTime();
            Date today = Calendar.getInstance().getTime();
            request.setAttribute("maxDay", format.format(maxDay));
            request.setAttribute("today", format.format(today));
            try {
                request.getServletContext().getRequestDispatcher("/usersView/client/newRequest.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
