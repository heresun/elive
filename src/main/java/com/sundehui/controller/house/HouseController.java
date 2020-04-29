package com.sundehui.controller.house;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sundehui.domain.House;
import com.sundehui.domain.Image;

import com.sundehui.domain.Transaction;
import com.sundehui.domain.User;
import com.sundehui.domain.help.FilterParams;
import com.sundehui.service.HouseService;
import com.sundehui.service.ImageService;
import com.sundehui.service.TransactionService;
import com.sundehui.service.UserService;
import com.sundehui.util.Constants;
import com.sundehui.util.HouseType;
import com.sundehui.util.ImgUtil;
import com.sundehui.util.Utils;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.net.www.HeaderParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.CodingErrorAction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/house")
@RestController
public class HouseController {


    @Autowired
    private HouseService service;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    // 根据房屋id获取房屋的全部信息
    @GetMapping(value = "/getOne", produces = "text/html;charset=UTF-8")
    public String findOne(HttpServletRequest request) {

        Integer id = null;
        String paramId = request.getParameter("id");
        if (paramId != null) {
            id = Integer.parseInt(paramId);
        }

        House houseSale = service.selectByPrimaryKey(id);

        if (houseSale == null) {
            return "err";
        }
        // 将图片的真实路径转换为url
        if (houseSale.getUrl() != null && houseSale.getUrl().length() > 0) {
            String url = ImgUtil.realPathToUrl(request, houseSale.getUrl());
            houseSale.setUrl(url);
        } else {
            // 如果当前房屋没有图片则默认图片为logo
            houseSale.setUrl(ImgUtil.getBaseUrl(request) + "upload/logo.png");
        }

        String str = JSON.toJSONString(houseSale);
        return str;
    }


    // 发布房源
    @PostMapping("/addOne")
    public int insertOne(HttpServletRequest request) {

        System.out.println("===========================================================");
        // 获取session域对象，从中获取图片的真实路径
        HttpSession session = request.getSession(false);
        int i = 0;

        String house = request.getParameter("house");
        if (house == null) {
            return 0;
        }

        JSONObject jsonObject = JSON.parseObject(house);
        House houseSale = JSON.toJavaObject(jsonObject, House.class);


        // 获取房屋编号
        String houseNumber = Utils.getHouseNumber(14);
        houseSale.setHouseNumber(houseNumber);


        System.out.println(houseSale);


        if (session != null) {
            // 获取用户id
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            System.out.println(user);
            if (user != null) {
                houseSale.setOwnerId(user.getId());
            }

            // 执行插入
            i = service.insertSelective(houseSale);

            List<String> fileNames = (List<String>) session.getAttribute(Constants.FILE_NAMES);
            System.out.println(fileNames);
            fileNames.forEach(item -> {
                // 创建图片对象
                Image image = new Image();
                image.setUri(item);
                image.setHouseNumber(houseNumber);
                imageService.insertSelective(image);

            });
            //存储完毕，销毁session中的fileNames这个属性
            session.removeAttribute(Constants.FILE_NAMES);
        }
        System.out.println("end");
        return i;
    }


    // 分页获取房屋信息
    @PostMapping("/housePage")
    public Map<String, Object> getSale(HttpServletRequest request) {
        System.out.println("/housePage");
        FilterParams paramMap = Utils.filterParam(request);

        List<House> housePage = service.getHousePage(paramMap);
        int houseCount = service.getHouseCount(paramMap);

        if (housePage == null || housePage.size() <= 0) {
            return null;
        }

        System.out.println(housePage);
        // 迭代，将房屋图片的真实路径替换为url
        housePage.forEach(item -> {
            if (item != null) {
                if (item.getUrl() != null && item.getUrl().length() != 0) {
                    String imgUrl = ImgUtil.realPathToUrl(request, item.getUrl());
                    item.setUrl(imgUrl);
                }
            }
        });
        HashMap<String, Object> res = new HashMap<>();
        res.put("housePage", housePage);
        res.put("houseCount", houseCount);

        return res;

    }


    //     获取现有的租房数量
    @GetMapping("/houseCount")
    public Integer getHouseCount(HttpServletRequest request) {

        System.out.println("/houseCount");
        FilterParams param = Utils.filterParam(request);
//
//        int type = 0;
//        String param = request.getParameter("type");
//        if (param != null) {
//            type = Integer.parseInt(param);
//        }

        int houseCount = service.getHouseCount(param);
        return houseCount;
    }

    // 该接口用于获取某个用户发布的房源信息 
    @GetMapping("/published")
    public List<House> getPublished(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute(Constants.USER_SESSION);
        }
        if (user != null) {
            Integer id = user.getId();
            List<House> houses = service.getPublishedByUserId(id);
            return houses;

        }
        return null;
    }

    // 该接口用于更新房屋信息
    @PostMapping("/update")
    public int updateHouseInfo (HttpServletRequest request){
        System.out.println("===========================================================");
        // 获取session域对象，从中获取图片的真实路径
        HttpSession session = request.getSession(false);
        int i = 0;
        String house = request.getParameter("house");
        System.out.println(house);
        if (house == null) {
            return 0;
        }


        JSONObject jsonObject = JSON.parseObject(house);
        House houseSale = JSON.toJavaObject(jsonObject, House.class);

        System.out.println(houseSale);
        String houseNumber = houseSale.getHouseNumber();



        if (session != null) {
            // 执行更新
            i = service.updateByPrimaryKeySelective(houseSale);

            List<String> fileNames = (List<String>) session.getAttribute(Constants.FILE_NAMES);
            fileNames.forEach(item -> {
                // 创建图片对象
                Image image = new Image();
                image.setUri(item);
                image.setHouseNumber(houseNumber);
                imageService.insertSelective(image);

            });
            //存储完毕，销毁session中的fileNames这个属性
            session.removeAttribute(Constants.FILE_NAMES);
        }
        System.out.println("end");
        return i;

    }

    // 通过房屋编号删除一个房源，并且删除成功后再根据房屋编号删除该房源对应的图片
    @GetMapping("/deleteOne")
    public int deleteOne (HttpServletRequest request){

        System.out.println("/house/deleteOne");
        String houseNumber = request.getParameter("houseNumber");
        if (houseNumber == null){
            return 0;
        }

        Integer i = service.deleteByHouseNumber(houseNumber);

        System.out.println("=========="+i);
        if (i > 0){
            imageService.deleteByHouseNumber(houseNumber);
            return i;
        }
        return 0;
    }

    // 通过房屋编号将一个房源标记为已售出
    @GetMapping("/saleOne")
    @Transactional
    public int saleOne (HttpServletRequest request){
        System.out.println("/house/delete");
        String houseNumber = request.getParameter("houseNumber");
        String buyerAccount = request.getParameter("buyerAccount");
        HttpSession session = request.getSession(false);
        if (houseNumber == null || buyerAccount ==null || session==null){
            return 0;
        }

        User user = (User) session.getAttribute(Constants.USER_SESSION);
        Integer userId = user.getId();
        // 通过买家的账户，查询出买家的id
        Integer buyerId = userService.getUidByAccount(buyerAccount);


        Transaction transaction = new Transaction();
        transaction.setSellerId(userId);
        transaction.setBuyerId(buyerId);
        transaction.setHouseNumber(houseNumber);
        Integer i = service.markHouseSold(houseNumber);

        if (i>0){
            int i1 = transactionService.insertSelective(transaction);
            return i1;
        }



        return i;

    }
}
