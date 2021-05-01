package com.epam.web;

import com.epam.service.ServletDestination;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoggedInFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        final String LOGINPAGE = httpServletRequest.getContextPath() + ServletDestination.LOGINPAGE.getPath();
        final String LOGINERROR = httpServletRequest.getContextPath() + ServletDestination.LOGINERROR.getPath();
        final String AUTHPAGE = httpServletRequest.getContextPath() + ServletDestination.AUTHPAGE.getPath();
        final String SIGNUPERROR = httpServletRequest.getContextPath() + ServletDestination.SIGNUPERROR.getPath();
        final String SIGNUPSUCC = httpServletRequest.getContextPath() + ServletDestination.SIGNUPSUCC.getPath();
        final String SIGNUPPAGE = httpServletRequest.getContextPath() + ServletDestination.SIGNUPPAGE.getPath();
        final String HOMEPAGE = httpServletRequest.getContextPath() + ServletDestination.HOMEPAGE.getPath();
        boolean loggedIn = session != null && session.getAttribute("login")!=null;
        boolean logInRequest = httpServletRequest.getRequestURI().equals(LOGINPAGE) ||
                httpServletRequest.getRequestURI().equals(LOGINERROR) ||
                httpServletRequest.getRequestURI().equals(AUTHPAGE) ||
                httpServletRequest.getRequestURI().equals(SIGNUPERROR) ||
                httpServletRequest.getRequestURI().equals(SIGNUPSUCC) ||
                httpServletRequest.getRequestURI().equals(SIGNUPPAGE);
        if(loggedIn && logInRequest){
            httpServletResponse.sendRedirect(HOMEPAGE);
        }else{
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
