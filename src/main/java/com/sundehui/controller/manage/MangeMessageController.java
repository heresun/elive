package com.sundehui.controller.manage;


import com.sundehui.domain.HouseMessage;
import com.sundehui.domain.UserMessage;
import com.sundehui.service.HouseMessageService;
import com.sundehui.service.HouseService;
import com.sundehui.service.UserMessageService;
import com.sundehui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/manage/message")
@RestController
public class MangeMessageController {// 这个控制器是管理员操作审核消息的

    @Autowired
    private HouseMessageService houseMessageService;
    @Autowired
    private UserMessageService userMessageServive;

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

    // 写入一条房屋相关信息
    @PostMapping("/addHouseMsg")
    public String addHouseMsg (HttpServletRequest request){

        String paramUserId = request.getParameter("userId");
        String houseNumber = request.getParameter("houseNumber");
        String content = request.getParameter("content");
        Integer userId = paramUserId == null?null:Integer.parseInt(paramUserId);

        if (userId == null){
            return "err";
        }
        HouseMessage houseMessage = new HouseMessage();
        houseMessage.setContent(content);
        houseMessage.setHouseNumber(houseNumber);
        houseMessage.setUserId(userId);
        int i = houseMessageService.insertSelective(houseMessage);
        if (i > 0){
            int resCode = houseService.changeStatus(houseNumber);
            if (resCode > 0){
                return "ok";
            }
        }

        return "err";
    }

    @PostMapping("/addUserMsg")
    public String addUserMsg (HttpServletRequest request){
        String paramUserId = request.getParameter("userId");
        String content = request.getParameter("content");
        Integer userId = paramUserId == null ? null: Integer.parseInt(paramUserId);

        if (userId == null){
            return "err";
        }

        UserMessage userMessage = new UserMessage();
        userMessage.setContent(content);
        userMessage.setUserId(userId);

        int i = userMessageServive.insertSelective(userMessage);
        if (i > 0){
            int i1 = userService.changeStatus(userId);
            if(i1>0){
                return "ok";
            }
        }

        return "err";

    }
}
