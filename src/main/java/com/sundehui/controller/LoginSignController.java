package com.sundehui.controller;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.User;
import com.sundehui.service.UserService;
import com.sundehui.util.Constants;
import com.sundehui.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class LoginSignController {
    @Autowired
    private UserService service;

    @PostMapping(value = "/login",produces="text/html;charset=UTF-8")
    public String login(HttpServletRequest request) {

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        System.out.println(account + " " + password);
        User user = service.selectByAccount(account);
        if (user == null) {
            return "err";
        } else if (user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute(Constants.USER_SESSION, user);
            user.setPassword("");
            return JSON.toJSONString(user);
        } else {
            return "err";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session!=null){
            session.removeAttribute(Constants.USER_SESSION);
        }
        return "ok";
    }

    @PostMapping("/sign")
    public String sign(HttpServletRequest request) throws ExecutionException, InterruptedException {
        //用户头像给一个默认的，等注册成功后，再修改
        //@RequestParam("file") MultipartFile[] files,
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        //获取默认头像的真实路径
        String defaultPhoto = request.getSession().getServletContext().getRealPath("/upload/logo.jpg");

        User user = new User(account, password, username, phone,defaultPhoto);
        System.out.println(user);
        int insert = service.insertSelective(user);
        if (insert > 0) {
            return "ok";
        }
        return "err";
    }


    @PostMapping("/uniqueVer")
    public String uniqueVerification (HttpServletRequest request){
        String account = request.getParameter("account");
        User user = service.selectByAccount(account);
        if (user==null){
            return "ok";
        }else {
            return "err";
        }
    }

    @GetMapping("/print")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/upload/logo.jpg");
        System.out.println(realPath);
    }

}
