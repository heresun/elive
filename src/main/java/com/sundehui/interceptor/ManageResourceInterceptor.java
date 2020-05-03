package com.sundehui.interceptor;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.InterceptorMsg;
import com.sundehui.domain.User;
import com.sundehui.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ManageResourceInterceptor implements HandlerInterceptor {

    @Autowired
    private InterceptorMsg msg;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        PrintWriter writer = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");


        if (session!=null){
            User  user = (User) session.getAttribute(Constants.USER_SESSION);
            if (user!=null){
                String roleName = user.getRoleName();
                if (roleName!="admin"){

                    msg.setStatus(-1);
                    msg.setMsg("您没有权限访问该资源!");
                    writer.write(JSON.toJSONString(msg));
                    writer.flush();
                    writer.close();
                    return false;
                }else {
                    return true;
                }
            }else {
                msg.setStatus(0);
                msg.setMsg("您尚未登录，请登录!");
                writer.write(JSON.toJSONString(msg));
                writer.flush();
                writer.close();
                return false;
            }

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
