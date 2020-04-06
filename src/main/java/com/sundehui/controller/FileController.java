package com.sundehui.controller;


import com.sundehui.util.Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/upload")
public class FileController {

    @PostMapping("/img")
    public String test(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "file", required = false) MultipartFile[] files) throws ExecutionException, InterruptedException, IOException {


        String file1 = request.getParameter("file");
        System.out.println(files);
        System.out.println(files.length);
        System.out.println("img");


        List<String> s = Utils.saveFile(files, request);
        System.out.println(s);


        return "ok";
    }
}
