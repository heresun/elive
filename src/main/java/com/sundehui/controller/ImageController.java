package com.sundehui.controller;


import com.alibaba.fastjson.JSON;
import com.sundehui.service.ImageService;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("/image")
@RestController
public class ImageController {

    @Autowired
    private ImageService service;

    @GetMapping(value = "/url",produces = "text/html;charset=UTF-8")
    public String getImageUrls( HttpServletRequest request){

        String houseNumber = request.getParameter("houseNumber");
        if (houseNumber==null){
            return "err";
        }
        List<String> uris = service.selectByHouseNumber(houseNumber);
        List<String> urls = ImgUtil.realPathToUrl(request, uris);

        if (urls==null || urls.size()<=0){
            return "err";
        }
        return JSON.toJSONString(urls);
    }

    @GetMapping("/deleteOne")
    public int deleteOneImg (HttpServletRequest request) throws IOException {
        System.out.println("/image/deleteOne");
        // 获取参数
        String houseNumber = request.getParameter("houseNumber");
        String imageName = request.getParameter("imageName");
        String imgName = "%"+imageName;
        System.out.println(houseNumber+"   -----     "+imageName);

        String realPath = service.getImgRealPath(houseNumber, imgName);
        System.out.println("===>"+realPath);

        // 先从本地删除图片，在删除数据库中的图片记录
        if (realPath!=null){
            Integer integer = service.deleteOne(houseNumber, imgName);
            if (integer > 0){
                Files.deleteIfExists(Paths.get(realPath));
                return integer;
            }
        }

        return 0;

    }


}
