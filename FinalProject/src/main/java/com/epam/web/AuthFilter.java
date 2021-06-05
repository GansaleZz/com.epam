package com.epam.web;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

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

@WebFilter("/*")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        try{
            if(httpServletRequest.getRequestURI().equals("/")){
                throw new Exception();
            }
            boolean loggedIn = session != null && session.getAttribute("login") != null;
            boolean badRequestLogged = (httpServletRequest.getRequestURI().contains("/controller") &&
                            httpServletRequest.getQueryString() != null &&
                            (httpServletRequest.getQueryString().contains("SIGNUP") ||
                            httpServletRequest.getQueryString().contains("LOGIN") ||
                            httpServletRequest.getQueryString().contains("AUTH") ||
                            httpServletRequest.getQueryString().contains("BAN")));
            if(httpServletRequest.getRequestURI().matches(".*(css|jpg|png|gif|js)")){
                try {
                    chain.doFilter(httpServletRequest,httpServletResponse);
                } catch (ServletException e) {
                    e.printStackTrace();
                }
                return;
            }else {
                if (loggedIn) {
                    UserDaoImpl userDao = new UserDaoImpl();
                    UserCriteria userCriteria = new UserCriteria();
                    userCriteria.setLogin((String) session.getAttribute("login"));
                    try {
                        if (userDao.findUserByCriteria(userCriteria).get().getStatus().equals(UserStatus.BANNED)) {
                            httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWBAN");
                            session.invalidate();
                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                }
                if (loggedIn && badRequestLogged) {
                    httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWHOME");
                } else {
                    if (!loggedIn && !badRequestLogged) {
                        httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWAUTH");
                    } else {
                        try {
                            chain.doFilter(request, response);
                        } catch (ServletException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch(Exception e){
            httpServletResponse.sendRedirect("http://localhost:8080/controller?command=ACTSHOWERROR");
        }
    }
}
