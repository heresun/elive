package com.sundehui.controller.other;


import com.alibaba.fastjson.JSON;
import com.sundehui.util.Constants;
import com.sundehui.util.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/upload")
public class FileController {

    @PostMapping("/img")
    public String test(HttpServletRequest request,
                       @RequestParam(value = "files", required = false) MultipartFile[] files) throws ExecutionException, InterruptedException, IOException {


        System.out.println("img");

        // 存储文件，并返回文件的真实路径列表
        List<String> fileNames = Utils.saveFile(files, request);
        System.out.println(fileNames);

        // 将放回的路径列表放入session中
        HttpSession session = request.getSession();
        session.setAttribute(Constants.FILE_NAMES,fileNames);

        return "ok";
    }
}
