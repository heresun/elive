package com.sundehui.controller.other;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.util.Base64Decoder;
import com.sundehui.domain.User;
import com.sundehui.service.UserService;
import com.sundehui.util.Constants;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.UUID;

@RestController
@RequestMapping("/util")
public class UtilController {

    @Autowired
    private UserService userService;

    @PostMapping("/sleep")
    public String sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    // 判断当前用户是否登录，前端每次刷新前进行更新
    @GetMapping(value = "/isLogin", produces = "text/html;charset=UTF-8")
    public String isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "false";
        } else {
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            if (user == null) {
                return "false";
            } else {
                user.setPhoto(ImgUtil.realPathToUrl(request, user.getPhoto()));
                return JSON.toJSONString(user);
            }
        }
    }

    // 保存前台的globalCityId数据
    @PostMapping(value = "/globalCity", produces = "text/html;charset=UTF-8")
    public String globalCityId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String cityInfo = request.getParameter("cityInfo");
        String attribute = (String) session.getAttribute(Constants.GLOBAL_CITY);

        if (attribute == null && cityInfo != null) {
            session.setAttribute(Constants.GLOBAL_CITY, cityInfo);
            return "ok";
        } else if (attribute != null && cityInfo != null) {
            session.setAttribute(Constants.GLOBAL_CITY, cityInfo);
            return "ok";
        } else if (attribute != null) {
            return attribute;
        }

        return "err";
    }

    // 旧密码验证
    @PostMapping("/checkUsedPwd")
    public String checkUsedPwd(HttpServletRequest request) {
        String usedPwd = request.getParameter("usedPwd");
        HttpSession session = request.getSession(false);

        if (session == null || usedPwd == null) {
            return "err";
        }
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        User userTemp = userService.selectByPrimaryKey(user.getId());
        if (userTemp == null) {
            return "err";
        }
        if (usedPwd.equals(userTemp.getPassword())) {
            return "ok";
        } else {
            return "err";
        }

    }

    @PostMapping("/updateTextInfo")
    public String updateTextInfo(HttpServletRequest request) {

        String newPhone = request.getParameter("newPhone");
        String newName = request.getParameter("newName");
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "err";
        }

        User user = (User) session.getAttribute(Constants.USER_SESSION);

        if (user == null) {
            return "err";
        }

        Integer id = user.getId();
        User user1 = new User();

        user1.setPhone(newPhone);
        user1.setUsername(newName);
        user1.setId(id);

        int i = userService.updateByPrimaryKeySelective(user1);
        if (i > 0) {
            if (newName != null) {
                user.setUsername(newName);
            }
            if (newPhone != null) {
                user.setPhone(newPhone);
            }
            return "ok";
        }
        return "err";
    }

    @PostMapping("/updateUserPhoto")
    public String updateUserPhoto(HttpServletRequest request) {
        System.out.println("/util/updateUserPhoto");
        String newPhoto = request.getParameter("newPhoto");
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "err";
        }
        User user = (User) session.getAttribute(Constants.USER_SESSION);

        if (user == null) {
            return "err";
        }

        // 获取真实路径，获取uuName，指定图片拓展名
        String realPath = request.getSession().getServletContext().getRealPath("/upload/");
        String uuStrName = UUID.randomUUID().toString();
        StringBuilder builder = new StringBuilder();
        builder.append(realPath);
        builder.append(uuStrName);
        builder.append(".jpeg");

        String filePath = builder.toString();
        try (   // 获取文件输出流
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ) {

            //截取内容部分。并对其编码
            String[] split = newPhoto.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decode = decoder.decodeBuffer(split[1]);

            fileOutputStream.write(decode);
            fileOutputStream.flush();

            User userTemp = userService.selectByPrimaryKey(user.getId());
            String photo = userTemp.getPhoto();
            try {
                Files.deleteIfExists(Paths.get(photo));
            } catch (IOException e) {
                System.out.println("要删除的图片不存在");
                e.printStackTrace();
            }finally {
                userTemp.setPhoto(filePath);
                //将内存的用户数据中的图片数据替换为新的路径
                String s = ImgUtil.realPathToUrl(request, filePath);
                user.setPhoto(s);
                userService.updateByPrimaryKeySelective(userTemp);
                return "ok";
            }


        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return "err";
        } catch (IOException e) {
            e.printStackTrace();
            return "err";
        }
    }

    @PostMapping("/updatePwd")
    public String updatePwd(HttpServletRequest request) {
        String newPwd = request.getParameter("newPwd");
        HttpSession session = request.getSession(false);

        if (session == null || newPwd == null) {
            return "err";
        }
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        if (user == null) {
            return "err";
        }
        user.setPassword(newPwd);
        int i = userService.updateByPrimaryKeySelective(user);

        if (i > 0) {
            System.out.println("4=========");
            session.removeAttribute(Constants.USER_SESSION);
            return "ok";
        }
        return "err";
    }
}
