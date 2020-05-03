package com.sundehui.controller;

import com.sundehui.domain.User;
import com.sundehui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping("/getOne")
    public List<User> findOne(HttpServletRequest request){
        ArrayList<User> users = new ArrayList<>();
        String ownerId = request.getParameter("ownerId");
        int id = Integer.parseInt(ownerId);
        User user = service.selectByPrimaryKey(id);
        user.setRoleName("");
        user.setRoleId(null);
        user.setId(null);
        users.add(user);
        return users;
    }

    // 通过账户，查询是否存在该用户
    @GetMapping("/checkAccount")
    public Integer checkAccount (HttpServletRequest request){
        String account = request.getParameter("account");
        if (account == null){
            return 0;
        }

        Integer i = service.checkAccount(account);
        return i;
    }
}
