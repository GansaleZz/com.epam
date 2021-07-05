package com.epam.web;

import com.epam.controller.ServletDestination;
import com.epam.criteria.impl.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.UserStatus;
import com.epam.exceptions.PageException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LogedInFilter implements Filter {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LogedInFilter.class);

    /**
     * Filter that checks user for logged in when he try to get access
     * to the pages that unavailable for authorized one
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        try{
            if(httpServletRequest.getRequestURI().equals("/")){
                throw new PageException();
            }
            boolean loggedIn = session != null && session.getAttribute("login") != null;
            boolean badRequestLogged = (httpServletRequest.getRequestURI().contains("/controller") &&
                    httpServletRequest.getQueryString() != null &&
                    (httpServletRequest.getQueryString().contains("SIGNUP") ||
                            httpServletRequest.getQueryString().contains("LOGIN") ||
                            httpServletRequest.getQueryString().contains("AUTH") ||
                            httpServletRequest.getQueryString().contains("BAN")));
            if(httpServletRequest.getRequestURI().matches(".*(css|jpg|png|gif|js)")){
                chain.doFilter(request,response);
                return;
            }else {
                if (loggedIn) {
                    UserDaoImpl userDao = new UserDaoImpl();
                    UserCriteria userCriteria = new UserCriteria();
                    userCriteria.setLogin((String) session.getAttribute("login"));
                    if (userDao.findUserByCriteria(userCriteria).get().getStatus().equals(UserStatus.BANNED)) {
                        httpServletRequest.getServletContext().getRequestDispatcher(ServletDestination.BANPAGE.getPath()).forward(request,response);
                        session.invalidate();
                    }
                }
                if (loggedIn && badRequestLogged) {
                    httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWHOME");
                } else {
                    chain.doFilter(request, response);
                }
            }
        }catch(Exception e){
            httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWERROR");
            LOGGER.debug(e.getMessage());
        }
    }
}