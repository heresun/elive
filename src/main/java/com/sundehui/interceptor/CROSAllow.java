package com.sundehui.interceptor;

import com.sundehui.domain.User;
import com.sundehui.util.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.invoke.ConstantCallSite;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CROSAllow implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] allowDomains = {
              "http://localhost:800",
                "*"
        };
        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
        String originHeaders = request.getHeader("Origin");
        System.out.println(originHeaders);
        System.out.println("拦截器");
        if (allowOrigins.contains(originHeaders)){
            System.out.println(originHeaders);
            response.setHeader("Access-Control-Allow-Origin", originHeaders);
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT,PATCH");
            response.setHeader("Access-Control-Max-Age", "36000");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,authorization");
            response.setHeader("Access-Control-Allow-Credentials","true");
            HttpSession session = request.getSession(false);
            if (session!=null){
                User user = (User) session.getAttribute(Constants.USER_SESSION);
                System.out.println(user);
            }

            String method = request.getMethod();

            if (method.equals("OPTIONS")) {
                response.setStatus(200);
            }


            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
