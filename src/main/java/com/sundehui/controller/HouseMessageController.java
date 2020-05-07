package com.sundehui.controller;


import com.alibaba.fastjson.JSON;
import com.sundehui.domain.HouseMessage;
import com.sundehui.domain.User;
import com.sundehui.service.HouseMessageService;
import com.sundehui.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/houseMessage")
@RestController
public class HouseMessageController {

    @Autowired
    private HouseMessageService service;

    @GetMapping(value = "/getAll",produces = "text/html;charset=UTF-8")
    public String getAll (HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session==null){
            return "err";
        }
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        Integer uId = user.getId();

        List<HouseMessage> all = service.getAll(uId);
        String string = JSON.toJSONString(all);
        return string;
    }

    @GetMapping("/deleteOne")
    public int deleteOne(HttpServletRequest request){
        String paramId = request.getParameter("id");
        Integer id = paramId == null ? 0:Integer.parseInt(paramId);
        int i = service.deleteOne(id);
        return i;

    }

    @GetMapping("/changeStatus")
    public int changeStatus (HttpServletRequest request){
        String paramId = request.getParameter("id");
        Integer id = paramId == null ? 0:Integer.parseInt(paramId);
        int i = service.changeStatus(id);
        return i;
    }

}
