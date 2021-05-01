package com.epam.web;

import com.epam.service.ServletDestination;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/usersView/moderator/*")
public class ModeratorFilter implements Filter {
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

        boolean loggedIn = session != null && session.getAttribute("login") != null;
        boolean logInRequest = httpServletRequest.getRequestURL().equals(LOGINPAGE);

        if(loggedIn && !session.getAttribute("userRole").equals("MODERATOR")){
            if(session.getAttribute("userRole").equals("CLIENT")){
                httpServletResponse.sendRedirect(CLIENTHOMEPAGE);
            }else{
                chain.doFilter(request,response);
            }
        }else {
            if (loggedIn || logInRequest) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendRedirect(LOGINPAGE);
            }
        }
    }
}
