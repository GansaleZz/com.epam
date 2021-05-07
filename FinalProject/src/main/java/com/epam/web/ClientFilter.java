package com.epam.web;

import com.epam.service.ServletDestination;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/usersView/client/*")
public class ClientFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequestWrapper) request;
        HttpServletResponse httpServletResponse = (HttpServletResponseWrapper) response;
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
}
