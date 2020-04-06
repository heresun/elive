package com.sundehui.controller;


import com.sundehui.domain.Collection;
import com.sundehui.domain.House;
import com.sundehui.service.CollectionService;
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
        String account = request.getParameter("account");
        List<House> allCollection = service.getAllCollection(account);
        return allCollection;

    }

    // 通过collections表的id，删除某个用户的一个收藏
    @DeleteMapping("/deleteOne")
    public String deleteOne(HttpServletRequest request) {
        String id1 = request.getParameter("id");
        System.out.println(id1);
        Integer id = Integer.parseInt(id1);
        int i = service.deleteByPrimaryKey(id);
        System.out.println("............" + i);
        if (i > 0) {
            return "ok";
        } else {
            return "err";
        }
    }

    // 添加一个收藏
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
        // 获取参数
        String houseId = request.getParameter("houseId");
        if (houseId==null){
            return -1;
        }

        int id = Integer.parseInt(houseId);
        int i = service.selectCollectedCount(id);
        return i;

    }
}
