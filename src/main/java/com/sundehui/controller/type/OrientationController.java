package com.sundehui.controller.type;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.Orientation;
import com.sundehui.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/type/orientation")
@RestController
public class OrientationController {

    @Autowired
    private TypeService service;

    @GetMapping(value = "/getAll",produces = "text/html;charset=UTF-8")
    public String getALl (){
        List<Orientation> allOrientationType = service.getAllOrientationType();
        return JSON.toJSONString(allOrientationType);
    }

    @GetMapping(value = "/getOne",produces = "text/html;charset=UTF-8")
    public String getOne (HttpServletRequest request){

        String paramId = request.getParameter("id");
        int id = 0;
        if (paramId!=null){
            id = Integer.parseInt(paramId);
        }

        Orientation oneOrientation = service.getOneOrientation(id);
        return oneOrientation.getDirection();
    }

}
