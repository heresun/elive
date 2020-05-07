package com.sundehui.controller;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.User;
import com.sundehui.domain.UserProve;
import com.sundehui.service.UserProveService;
import com.sundehui.service.UserService;
import com.sundehui.util.Constants;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserProveService userProveService;


    @GetMapping("/getOne")
    public List<User> findOne(HttpServletRequest request) {
        ArrayList<User> users = new ArrayList<>();
        String ownerId = request.getParameter("ownerId");
        int id = Integer.parseInt(ownerId);
        User user = service.selectByPrimaryKey(id);
        user.setRoleName(null);
        user.setPassword(null);
        user.setRoleId(null);
        user.setId(null);
        users.add(user);
        return users;
    }

    // 通过账户，查询是否存在该用户
    @GetMapping("/checkAccount")
    public Integer checkAccount(HttpServletRequest request) {
        String account = request.getParameter("account");
        if (account == null) {
            return 0;
        }

        Integer i = service.checkAccount(account);
        return i;
    }

    @GetMapping("/addProve")
    public String addProve(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "err";
        }

        User user = (User) session.getAttribute(Constants.USER_SESSION);

        if (user == null) {
            return "err";
        }

        Integer id = user.getId();
        List<String> files = (List<String>) session.getAttribute(Constants.FILE_NAMES);

        UserProve userProve = new UserProve();
        userProve.setUserId(id);

        files.forEach(item->{
                userProve.setUri(item);
                userProveService.insertSelective(userProve);
        });

        session.removeAttribute(Constants.FILE_NAMES);

        return "ok";

    }

    @GetMapping("/getAllProve")
    public String getAllProve (HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session==null){
            return "err";
        }
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        Integer id = user.getId();

        List<UserProve> proves = userProveService.selectByUserId(id);
        List<String> urls = new ArrayList<>();
        proves.forEach(item->{
            if (item!=null){
                String s = ImgUtil.realPathToUrl(request, item.getUri());
                urls.add(s);
            }
        });
        String string = JSON.toJSONString(urls);
        return string;
    }

    @GetMapping("/deleteOneProve")
    public String deleteOneProve (HttpServletRequest request){
        String imageName = request.getParameter("imageName");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Constants.USER_SESSION);

        Integer uId = user.getId();

        int i = userProveService.deleteOneProve(imageName, uId);

        if (i>0){
            return "ok";
        }
        return "err";
    }
}
