package com.sundehui.controller;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.HouseProve;
import com.sundehui.service.HouseProveService;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/houseProve")
public class HouseProveController {
    @Autowired
    private HouseProveService service;

    @GetMapping("/getAll")
    private String getAllHouseProve(HttpServletRequest request) {
        String houseNumber = request.getParameter("houseNumber");
        if (houseNumber == null) {
            return "err";
        }
        List<String> houseProves = service.getAll(houseNumber);
        String urls = JSON.toJSONString(houseProves);
        return urls;

    }

    @GetMapping("/deleteOne")
    private int deleteOne(HttpServletRequest request) {
        String imageName = request.getParameter("imageName");
        String houseNumber = request.getParameter("houseNumber");
        if (imageName == null || houseNumber == null) {
            return 0;
        }

        int i = service.deleteOne(houseNumber, imageName);

        return i;
    }
}
