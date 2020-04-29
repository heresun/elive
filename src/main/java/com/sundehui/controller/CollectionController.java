package com.sundehui.controller;


import com.sundehui.domain.Collection;
import com.sundehui.domain.House;
import com.sundehui.domain.User;
import com.sundehui.service.CollectionService;
import com.sundehui.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    CollectionService service;

    // 通过用户的账户获取该用户的所有收藏
    @GetMapping("/all")
    public List<House> getAllCollection(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if (user != null) {
            List<House> allCollection = service.getAllCollection(user.getAccount());
            return allCollection;
        }
        return null;
    }

    // 通过collections表的id，删除某个用户的一个收藏
    @GetMapping("/deleteOne")
    public String deleteOne(HttpServletRequest request) {
        int i = 0;
        String houseId = request.getParameter("houseId");
        String userId = request.getParameter("userId");
        String cId = request.getParameter("id");

        System.out.println(houseId + " " + userId);


        if (houseId != null && userId != null) {
            Integer hId = Integer.parseInt(houseId);
            Integer uId = Integer.parseInt(userId);
            i = service.deleteByHouseIdAndUserId(hId, uId);
        }

        if (cId != null) {
            Integer collectId = Integer.parseInt(cId);
            i = service.deleteByPrimaryKey(collectId);
        }
        System.out.println(cId);

        System.out.println("............" + i);
        if (i > 0) {
            return "ok";
        } else {
            return "err";
        }
    }


//    // 添加一个收藏
    @PostMapping("/addOne")
    public int addOne(HttpServletRequest request) {
        // 获取参数
        String houseId = request.getParameter("houseId");
        String userId = request.getParameter("userId");
        System.out.println(houseId + " ddd " + userId);
        if (houseId == null || userId == null) {
            return 0;
        }
        int idHouse = Integer.parseInt(houseId);
        int idUser = Integer.parseInt(userId);
        Collection collection = new Collection();
        collection.setHouseId(idHouse);
        collection.setUserId(idUser);

        int i = service.insertSelective(collection);
        if (i >= 1) {
            return collection.getId();
        } else {
            return 0;
        }
    }

    // 获取每套房子被收藏的次数
    @GetMapping("/getCount")
    public int selectCollectedCount(HttpServletRequest request) {

        int id = 0;
        String houseId = request.getParameter("houseId");
        if (houseId != null) {
            id = Integer.parseInt(houseId);
        }
        int i = service.selectCollectedCount(id);
        return i;

    }
}
