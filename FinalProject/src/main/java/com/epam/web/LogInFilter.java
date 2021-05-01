package com.epam.web;

import com.epam.service.ServletDestination;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/home/*")
public class LogInFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession(false);
        final String LOGINPAGEURL = httpServletRequest.getContextPath() + ServletDestination.LOGINPAGE.getPath();
        boolean loggedIn = session != null && session.getAttribute("login") != null;
        boolean logInRequest = httpServletRequest.getRequestURL().equals(LOGINPAGEURL);
        if (loggedIn || logInRequest) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect(LOGINPAGEURL);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
