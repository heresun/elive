package com.sundehui.controller.type;


import com.alibaba.fastjson.JSON;
import com.sundehui.domain.HeatingType;
import com.sundehui.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/type/heating")
@RestController
public class HeatingTypeController {

    @Autowired
    private TypeService service;

    @GetMapping(value = "/getAll",produces = "text/html;charset=UTF-8")
    public String getALl (){
        List<HeatingType> allHeatingType = service.getAllHeatingType();
        return JSON.toJSONString(allHeatingType);
    }



}
