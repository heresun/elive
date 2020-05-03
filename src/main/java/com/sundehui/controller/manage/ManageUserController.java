package com.sundehui.controller.manage;


import com.sundehui.controller.UserController;
import com.sundehui.controller.house.HouseController;
import com.sundehui.domain.User;
import com.sundehui.mapper.UserMapper;
import com.sundehui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 1.后台管理Api
@RestController
@RequestMapping("/manage/user")
public class ManageUserController {

    @Autowired
    private UserService service;

    @GetMapping("/count")
    public int getUserCount (HttpServletRequest request){
        String type = request.getParameter("type");
        if (type == null){
            return 0;
        }
        Integer i = Integer.parseInt(type);
        Integer resCount = service.getUserCount(i);
        return resCount;
    }
    // 获取所有用户，这里应该分页
    @GetMapping("/findAll")
    public List<User> findAllUser(HttpServletRequest request) {
        String paramPage = request.getParameter("page");
        String paramCount = request.getParameter("count");
        if (paramCount != null && paramCount != null) {
            int page = Integer.parseInt(paramPage);
            int count = Integer.parseInt(paramCount);
            List<User> all = service.findAll(page, count);
            return all;
        }
        return null;
    }

    // 根据用户账户模糊搜索用户
    @GetMapping("/findLike")
    public List<User> findLikeByAccount(HttpServletRequest request) {
        String account = request.getParameter("account");
        String paramPage = request.getParameter("page");
        String paramCount = request.getParameter("count");

        if (account != null && paramPage !=null && paramCount != null) {
            int page = Integer.parseInt(paramPage);
            int count = Integer.parseInt(paramCount);
            List<User> users = service.findLikeByAccount(account,page,count);
            return users;
        }
        return null;
    }

    // 将用户设置为审核通过
    @GetMapping("/changeExamineTye")
    public int passCheck (HttpServletRequest request){

        String paramId = request.getParameter("id");
        String paramExamineType = request.getParameter("examineType");
        if (paramId == null || paramExamineType == null){
            return 0;
        }
        int uId = Integer.parseInt(paramId);
        int examineType = Integer.parseInt(paramExamineType);
        int res = service.passCheck(uId, examineType);
        return res;
    }

}
