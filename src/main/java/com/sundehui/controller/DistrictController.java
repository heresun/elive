package com.sundehui.controller;

import com.sundehui.domain.District;
import com.sundehui.service.DistrictService;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService service;

    @GetMapping(value="/houseAddr", produces = "text/html;charset=UTF-8")
    public String selectByAreaId (HttpServletRequest request){
        // 获取参数id
        String areaId = request.getParameter("areaId");
        int id = Integer.parseInt(areaId);
        District district = service.selectByAreaId(id);
        StringBuilder builder = new StringBuilder();
        builder.append(district.getProvince());
        builder.append(district.getCity());
        builder.append(district.getArea());

        return builder.toString();
    }

}
