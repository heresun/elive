package com.sundehui.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.sundehui.domain.User;
import com.sundehui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService service;

    @GetMapping("/findAll")
    public List<User> login (HttpServletRequest request, HttpServletResponse response){
        List<User> all = service.findAll();
        System.out.println(all);
        return all;
    }
    @GetMapping("/findOne/{id}")
    public User findOne (@PathVariable("id") int id){
        User user = service.selectByPrimaryKey(id);
        System.out.println(user);
        return user;
    }
}
