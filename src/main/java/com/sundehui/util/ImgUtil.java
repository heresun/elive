package com.sundehui.util;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImgUtil {

    // 将数据库中图片的真实路径改为url
    public static List<String> realPathToUrl(HttpServletRequest request, List<String> list) {
        List<String> urls = new ArrayList<>();

        String baseUrl = getBaseUrl(request);

        list.forEach(item -> {
            int i = item.indexOf("upload");
            String[] strs = item.substring(i).split("\\\\");
            String url = baseUrl + String.join("/", strs);
            urls.add(url);
        });

        return urls;
    }

    public static String realPathToUrl(HttpServletRequest request, String realPath) {

        String baseUrl = getBaseUrl(request);


        int i = realPath.indexOf("upload");
        String[] strs = realPath.substring(i).split("\\\\");
        String url = baseUrl + String.join("/", strs);

        return url;
    }

    public static String getBaseUrl(HttpServletRequest request){
        String str = request.getRequestURL().toString();
        String[] split = str.split("/");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            builder.append(split[i]);
            builder.append("/");
        }
        String baseUrl = builder.toString();

        return baseUrl;
    }
}
