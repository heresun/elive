package com.sundehui.controller;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.Recommend;
import com.sundehui.domain.User;
import com.sundehui.service.RecommendService;
import com.sundehui.service.UserService;
import com.sundehui.util.Constants;
import com.sundehui.util.ImgUtil;
import com.sundehui.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class LoginSignController {
    @Autowired
    private UserService service;

    @Autowired
    private RecommendService recommendService;

    @PostMapping(value = "/login", produces = "text/html;charset=UTF-8")
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
            session.setAttribute(Constants.PAGE_ONE,1);
            session.setAttribute(Constants.PAGE_TWO,1);
            // 这里用来保存访问的房屋数量
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            session.setAttribute(Constants.ACCESS_LOG, hashMap);

            user.setPassword("");
            user.setPhoto(ImgUtil.realPathToUrl(request, user.getPhoto()));
            return JSON.toJSONString(user);
        } else {
            return "err";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute(Constants.ACCESS_LOG);
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            List<Recommend> recommends = recommendService.getAll(user.getId());
            System.out.println("quniadayhe的雄安百姓");
            System.out.println(recommends);

            if (map != null && recommends != null) {
                // 如果二者都不为空,才有意义
                ArrayList<Map.Entry<Integer, Integer>> maps = new ArrayList<>();

                map.entrySet().forEach(item -> {
                    maps.add(item);
                });

                System.out.println("==============dddddd===================");
                System.out.println(maps);

                if (recommends.size() <= 0) {// 如果还没有记录,则添加之
                        maps.forEach(item -> {
                            Recommend recommend = new Recommend();
                            recommend.setAreaId(item.getKey());
                            recommend.setTimes(item.getValue());
                            recommend.setUserId(user.getId());
                            //  写入
                            recommendService.insertSelective(recommend);
                        });

                } else {
                    if (maps.size() > 0){
                        ArrayList<Recommend> newRecommend = Utils.getMaxList(recommends, maps, user);

                        System.out.println("荒漠大妈骆驼");
                        System.out.println(newRecommend);

                        newRecommend.forEach(item->{
                            if (item.getId()==null){
                                recommendService.insertSelective(item);
                            }else {
                                recommendService.updateByPrimaryKeySelective(item);
                            }
                        });
                    }
                }
            }

            session.removeAttribute(Constants.USER_SESSION);
            return "ok";

        }
        return "err";
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

        User user = new User(account, password, username, phone, defaultPhoto);
        System.out.println(user);
        int insert = service.insertSelective(user);
        if (insert > 0) {
            return "ok";
        }
        return "err";
    }


    @PostMapping("/uniqueVer")
    public String uniqueVerification(HttpServletRequest request) {
        String account = request.getParameter("account");
        User user = service.selectByAccount(account);
        if (user == null) {
            return "ok";
        } else {
            return "err";
        }
    }

    @GetMapping("/print")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/upload/logo.jpg");
        System.out.println(realPath);
    }

}
