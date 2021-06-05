package com.epam.service;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewDepositPage implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(link + CommandInstance.ACTSHOWHOME);
        }else{
            try {
                request.getServletContext().getRequestDispatcher(ServletDestination.CLIENTNEWDEPOSITPAGE.getPath()).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
}
