package com.sundehui.controller.other;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.User;
import com.sundehui.util.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/util")
public class UtilController {


    @PostMapping("/sleep")
    public String sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    // 判断当前用户是否登录，前端每次刷新前进行更新
    @GetMapping(value = "/isLogin", produces = "text/html;charset=UTF-8")
    public String isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "false";
        } else {
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            if (user == null) {
                return "false";
            } else {
                return JSON.toJSONString(user);
            }
        }
    }

    // 保存前台的globalCityId数据
    @PostMapping(value = "/globalCity", produces = "text/html;charset=UTF-8")
    public String globalCityId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String cityInfo = request.getParameter("cityInfo");
        String attribute = (String) session.getAttribute(Constants.GLOBAL_CITY);

        if (attribute == null && cityInfo != null) {
            session.setAttribute(Constants.GLOBAL_CITY, cityInfo);
            return "ok";
        } else if (attribute != null && cityInfo != null) {
            session.setAttribute(Constants.GLOBAL_CITY, cityInfo);
            return "ok";
        } else if (attribute != null) {
            return attribute;
        }

        return "err";
    }


}
