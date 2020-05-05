package com.sundehui.controller;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.District;
import com.sundehui.domain.help.DistrictHelp;
import com.sundehui.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService service;

    // 根据pId获取省集合、市集合、地区集合
    @GetMapping(value = "/location", produces = "text/html;charset=UTF-8")
    public String selectByAreaId(HttpServletRequest request) {
        // 获取参数id
        String pId = request.getParameter("pId");

        int argId = 0;
        if (pId != null) {
            argId = Integer.parseInt(pId);

        }
        // 获取数
        List<District> districts = service.selectByPid(argId);

        //将获取到的数据打包成json格式
        ArrayList<DistrictHelp> districtHelps = new ArrayList<>();

        districts.forEach(item -> {
            districtHelps.add(new DistrictHelp(item.getId(), item.getDistrictName()));
        });


        String string = JSON.toJSONString(districtHelps);
        System.out.println(string);
        return string;
    }

    // 获取“省/市/地区”字符串
    @GetMapping(value = "/singleLocation", produces = "text/html;charset=UTF-8")
    public String getById(HttpServletRequest request) {
        // 获取参数id
        String paramProvinceId = request.getParameter("provinceId");
        String paramCityId = request.getParameter("cityId");
        String paramAreaId = request.getParameter("areaId");

        StringBuilder builder = new StringBuilder();
        int provinceId = 0;
        int cityId = 0;
        int areaId = 0;
        if (paramProvinceId != null || paramCityId != null || paramAreaId != null) {
            if (paramProvinceId != null) {

                provinceId = Integer.parseInt(paramProvinceId);
                District district = service.selectByPrimaryKey(provinceId);
                builder.append(district.getDistrictName());
            }
            if (paramCityId != null){
                builder.append("/");
                cityId = Integer.parseInt(paramCityId);
                District district = service.selectByPrimaryKey(cityId);
                builder.append(district.getDistrictName());
            }
            if (paramAreaId != null){
                builder.append("/");
                areaId = Integer.parseInt(paramAreaId);
                District district = service.selectByPrimaryKey(areaId);
                builder.append(district.getDistrictName());
            }
        } else {
            return null;
        }
        return builder.toString();

    }

}
