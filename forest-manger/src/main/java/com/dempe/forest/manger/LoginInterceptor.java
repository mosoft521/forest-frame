package com.dempe.forest.manger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2014/12/29
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("---------------------------------");
        LOGGER.info("******************************************");
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj == null || "".equals(obj.toString())) {
            response.sendRedirect("/static/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle -----------");


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}