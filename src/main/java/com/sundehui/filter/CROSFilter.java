package com.sundehui.filter;

import com.sundehui.domain.User;
import com.sundehui.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CROSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String[] allowDomains = {
//                "http://localhost:8081"
//        };
//        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
//        String originHeaders = request.getHeader("Origin");
//        if (allowOrigins.contains(originHeaders)){
//            System.out.println(originHeaders);
//            response.setHeader("Access-Control-Allow-Origin", originHeaders);
//            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT,PATCH");
//            response.setHeader("Access-Control-Max-Age", "36000");
//            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,authorization");
//            response.setHeader("Access-Control-Allow-Credentials","true");
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-Width,Content-Type,Accept");

        HttpSession session = request.getSession(false);
        if (session!=null){
            User user = (User) session.getAttribute(Constants.USER_SESSION);
                System.out.println(user);
        }

        System.out.println("过滤器");

        String method = request.getMethod();

        if (method.equals("OPTIONS")) {
            response.setStatus(200);
        }
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
