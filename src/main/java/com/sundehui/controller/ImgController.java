package com.sundehui.controller;

import com.sundehui.service.ImgService;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/img")
public class ImgController {
    @Autowired
    private ImgService service;

    @GetMapping("/getImgUrls")
    public List<String> getImgUris(HttpServletRequest request){
        // 获取参数
        String houseId = request.getParameter("houseId");
        int id = Integer.parseInt(houseId);
        //从service层获取数据
        List<String> uris = service.findByHouseId(id);
        // 将真实路径转换为url
        List<String> urls = ImgUtil.realPathToUrl(request, uris);
        return urls;
    }
}
