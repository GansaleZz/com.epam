package com.epam.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuthFilter.class);

    /**
     * Filter that checks user for logged in when he try to get access
     * to the pages that unavailable without authorization
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("login") != null;
        boolean badRequestLogged = (httpServletRequest.getRequestURI().contains("/controller") &&
                httpServletRequest.getQueryString() != null &&
                (httpServletRequest.getQueryString().contains("SIGNUP") ||
                        httpServletRequest.getQueryString().contains("LOGIN") ||
                        httpServletRequest.getQueryString().contains("AUTH") ||
                        httpServletRequest.getQueryString().contains("BAN")));
        try {
            if (httpServletRequest.getRequestURI().matches(".*(css|jpg|png|gif|js)")) {
                chain.doFilter(request,response);
                return;
            } else {
                if (!loggedIn && !badRequestLogged) {
                    httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWAUTH");
                } else {
                    chain.doFilter(request, response);
                }
            }
        }catch(ServletException e){
            httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWERROR");
            logger.error(e.getMessage());
        }
    }
}