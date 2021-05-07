package com.epam.web;

import com.epam.service.ServletDestination;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/usersView/admin/*")
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        final String LOGINPAGE = ServletDestination.LOGINPAGE.getPath();
        final String CLIENTHOMEPAGE = ServletDestination.CLIENTHOMEPAGE.getPath();
        final String MODERATORHOMEPAGE = ServletDestination.MODERATORHOMEPAGE.getPath();

        boolean loggedIn = session != null && session.getAttribute("login") != null;
        if(loggedIn){
            switch((String)session.getAttribute("userRole")){
                case "MODERATOR" -> httpServletResponse.sendRedirect(MODERATORHOMEPAGE);
                case "CLIENT" -> httpServletResponse.sendRedirect(CLIENTHOMEPAGE);
                default -> chain.doFilter(request,response);
            }
        }else {
                httpServletResponse.sendRedirect(LOGINPAGE);
        }
    }
}
