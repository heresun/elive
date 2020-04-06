package com.sundehui.controller;

import com.sundehui.domain.House;
import com.sundehui.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService service;
    
    @GetMapping("/getOne")
    public List<House> findOne(HttpServletRequest request){
        List<House> houses = new ArrayList<>();
        String id1 = request.getParameter("id");
        int id = Integer.parseInt(id1);
        House house = service.selectByPrimaryKey(id);
        houses.add(house);
        return houses;
    }
}
