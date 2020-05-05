package com.sundehui.controller.manage;


import com.alibaba.fastjson.JSON;
import com.sundehui.controller.UserController;
import com.sundehui.controller.house.HouseController;
import com.sundehui.domain.HouseProve;
import com.sundehui.domain.User;
import com.sundehui.domain.UserProve;
import com.sundehui.mapper.UserMapper;
import com.sundehui.service.HouseService;
import com.sundehui.service.UserProveService;
import com.sundehui.service.UserService;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

// 1.后台管理Api
@RestController
@RequestMapping("/manage/user")
public class ManageUserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserController userController;

    @Autowired
    private UserProveService proveService;

    @Autowired
    private HouseService houseService;

    @GetMapping(value = "/getOne", produces = "text/html;charset=UTF-8")
    public String getOne(HttpServletRequest request) {
        String paramOwnerId = request.getParameter("ownerId");
        Integer ownerId = paramOwnerId == null ? 0 : Integer.parseInt(paramOwnerId);
        if (ownerId == 0) {
            return "err";
        }

        System.out.println("/manage/user/getOne");
        User owner = userController.findOne(request).get(0);
        List<UserProve> houseProves = proveService.selectByUserId(ownerId);
        List<String> proveUrls = new ArrayList<>();
        houseProves.forEach(item -> {
            if (item != null) {
                proveUrls.add(ImgUtil.realPathToUrl(request, item.getUri()));
            }
        });
        owner.setProveUrls(proveUrls);

        String string = JSON.toJSONString(owner);
        return string;

    }

    @GetMapping("/count")
    public int getUserCount(HttpServletRequest request) {
        String type = request.getParameter("examineType");
        String account = request.getParameter("account");
        if (type == null) {
            return 0;
        }
        Integer i = Integer.parseInt(type);
        Integer resCount = service.getUserCount(i,account);
        return resCount;
    }

    // 获取所有用户，这里应该分页
    @GetMapping("/findAll")
    public List<User> findAllUser(HttpServletRequest request) {
        String paramPage = request.getParameter("page");
        String paramCount = request.getParameter("count");
        String examineType = request.getParameter("examineType");
        String account = request.getParameter("account");
        if (paramCount != null && paramCount != null && examineType != null) {
            int page = Integer.parseInt(paramPage);
            int count = Integer.parseInt(paramCount);
            int type = Integer.parseInt(examineType);
            List<User> all = service.findAll(page, count, type, account);
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
        String paramExamineType = request.getParameter("examineType");

        if (account != null && paramPage != null && paramCount != null) {
            Integer page = Integer.parseInt(paramPage);
            Integer count = Integer.parseInt(paramCount);
            Integer exmineType = paramExamineType == null ? null:Integer.parseInt(paramExamineType);
            List<User> users = service.findLikeByAccount(account, page, count,exmineType);
            return users;
        }
        return null;
    }

    // 将用户设置为审核通过
    @GetMapping("/changeExamineTye")
    public int passCheck(HttpServletRequest request) {

        String paramId = request.getParameter("id");
        String paramExamineType = request.getParameter("examineType");
        if (paramId == null || paramExamineType == null) {
            return 0;
        }
        int uId = Integer.parseInt(paramId);
        int examineType = Integer.parseInt(paramExamineType);
        // 如果将用户的状态设置为待审核，则将其发布的所有房源设置为待审核
        if(examineType == 0){
            houseService.changeExamineTypeByOwnerId(uId);
        }
        int res = service.passCheck(uId, examineType);
        service.changeStatus(uId);
        return res;
    }

}
