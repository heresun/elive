package com.sundehui.controller.type;


import com.alibaba.fastjson.JSON;
import com.sundehui.domain.ElectricType;
import com.sundehui.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/type/electric")
@RestController
public class ElectricTypeController {

    @Autowired
    private TypeService service;

    @GetMapping(value = "/getAll", produces = "text/html;charset=UTF-8")
    public String getAll (){
        List<ElectricType> all = service.getAllElectricType();

        String string = JSON.toJSONString(all);

        return string;
    }

}
