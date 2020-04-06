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

    // 1.后台管理Api
    @GetMapping("/findAll")
    public List<User> findAllUser(){

        return null;
    }

    @GetMapping("/getOne")
    public List<User> findOne(HttpServletRequest request){
        ArrayList<User> users = new ArrayList<>();
        String ownerId = request.getParameter("ownerId");
        int id = Integer.parseInt(ownerId);
        User user = service.selectByPrimaryKey(id);
        users.add(user);
        return users;
    }
}
