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

@WebFilter("/auth/*")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequestWrapper) request;
        HttpServletResponse httpServletResponse = (HttpServletResponseWrapper) response;
        HttpSession session = httpServletRequest.getSession(false);

        final String LOGINPAGE =ServletDestination.LOGINPAGE.getPath();
        final String LOGINERROR = ServletDestination.LOGINERROR.getPath();
        final String AUTHPAGE = ServletDestination.AUTHPAGE.getPath();
        final String SIGNUPERROR = ServletDestination.SIGNUPERROR.getPath();
        final String SIGNUPSUCC =  ServletDestination.SIGNUPSUCC.getPath();
        final String SIGNUPPAGE = ServletDestination.SIGNUPPAGE.getPath();
        final String ADMINHOMEPAGE =  ServletDestination.ADMINHOMEPAGE.getPath();
        final String CLIENTHOMEPAGE = ServletDestination.CLIENTHOMEPAGE.getPath();
        final String MODERATORHOMEPAGE = ServletDestination.MODERATORHOMEPAGE.getPath();
        boolean loggedIn = session != null && session.getAttribute("login") != null;

        boolean badRequest = httpServletRequest.getRequestURI().equals(LOGINPAGE) ||
                httpServletRequest.getRequestURI().equals(LOGINERROR) ||
                httpServletRequest.getRequestURI().equals(AUTHPAGE) ||
                httpServletRequest.getRequestURI().equals(SIGNUPERROR) ||
                httpServletRequest.getRequestURI().equals(SIGNUPSUCC) ||
                httpServletRequest.getRequestURI().equals(SIGNUPPAGE) ;
        if(loggedIn && badRequest){
            if(session.getAttribute("userRole").equals("ADMIN")) {
                httpServletResponse.sendRedirect(ADMINHOMEPAGE);
            }else{
                if(session.getAttribute("userRole").equals("CLIENT")){
                    httpServletResponse.sendRedirect(CLIENTHOMEPAGE);
                }else{
                    httpServletResponse.sendRedirect(MODERATORHOMEPAGE);
                }
            }
        }else{
            chain.doFilter(request,response);
        }
    }
}
