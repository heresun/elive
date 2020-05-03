package com.sundehui.interceptor;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.InterceptorMsg;
import com.sundehui.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ResourceInterceptor implements HandlerInterceptor {

    @Autowired
    private InterceptorMsg msg;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        if (session!=null){
            Object user = session.getAttribute(Constants.USER_SESSION);
            if (user!=null){
                return true;
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        msg.setStatus(0);
        msg.setMsg("请登录后操作");
        writer.write(JSON.toJSONString(msg));
        writer.flush();
        writer.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
