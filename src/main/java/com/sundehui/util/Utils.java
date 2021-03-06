package com.sundehui.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sundehui.domain.Recommend;
import com.sundehui.domain.User;
import com.sundehui.domain.help.FilterParams;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Utils {
    private static ExecutorService pool;
    private static ReentrantLock lock = new ReentrantLock();

    static {
        pool = new ThreadPoolExecutor(
                3,
                5,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    //存储前端上传的文件
    public static List<String> saveFile(MultipartFile[] files, HttpServletRequest request) throws ExecutionException, InterruptedException {
        List<Future<String>> list = new ArrayList<>();
        List<String> fileNameList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            Future<String> submit = pool.submit(getRun(files[i], request));
            list.add(submit);
        }
        list.forEach(item -> {
            try {
                fileNameList.add(item.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        return fileNameList;
    }


    private static Callable<String> getRun(MultipartFile file, HttpServletRequest request) {
        return () -> {

            StringBuilder builder = new StringBuilder();
            String realPath = request.getSession().getServletContext().getRealPath("/upload/");


            builder.append(realPath);
            String string = UUID.randomUUID().toString();
            String[] split = file.getOriginalFilename().split("\\.");

            builder.append(string);
            builder.append(".");
            builder.append(split[split.length - 1]);


            String fileName = builder.toString();

            if (!file.isEmpty()) {
                try (
                        OutputStream os = new FileOutputStream(fileName);
                        InputStream is = file.getInputStream();
                ) {
                    //定义缓冲区大小
                    byte[] buffer = new byte[1024];

                    //声明从输入流一次获取的数据长度
                    int len = 0;
                    //输出
                    while ((len = is.read(buffer)) > 0) {
                        os.write(buffer, 0, len);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return fileName;
        };
    }

    // 生成随机字符串，该字符串作为房源编号,type用来指明房屋类型
    public static String getHouseNumber(int length) {

        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append("MF");
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    // 获取筛选房屋的条件
    public static FilterParams filterParam(HttpServletRequest request) {

//        String paramPage = request.getParameter("page");
//        String paramCount = request.getParameter("count");
//        String paramType = request.getParameter("type");
//        // flag用来表示查询的类型
//        String paramFlag = request.getParameter("flag");
//        // 获取省市区id
//        String paramProvinceId = request.getParameter("provinceId");
//        String paramCityId = request.getParameter("cityId");
//        String paramAreaId = request.getParameter("areaId");

        // 获取过滤条件
        String parameter = request.getParameter("filterParams");
        System.out.println("======================================过滤条件================================");
        System.out.println(parameter);
        System.out.println("======================================过滤条件映射为对象================================");
        JSONObject jsonObject = JSON.parseObject(parameter);
        FilterParams filterParams = JSON.toJavaObject(jsonObject, FilterParams.class);
        System.out.println(filterParams);

//        if (paramPage != null) {
//            paramMap.put("from",Integer.parseInt(paramPage));
//        }
//        if (paramCount != null) {
//            paramMap.put("count",Integer.parseInt(paramCount));
//        }
//        if (paramType != null) {
////            type = Integer.parseInt(paramType);
//            paramMap.put("type",Integer.parseInt(paramType));
//        }
//        if (paramProvinceId!=null){
////            provinceId = Integer.parseInt(paramProvinceId);
//            paramMap.put("provinceId",Integer.parseInt(paramProvinceId));
//        }
//        if (paramCityId !=null ){
////            cityId = Integer.parseInt(paramCityId);
//            paramMap.put("cityId",Integer.parseInt(paramCityId));
//        }
//        if (paramAreaId !=null){
////            areaId = Integer.parseInt(paramAreaId);
//            paramMap.put("areaId",Integer.parseInt(paramAreaId));
//        }
//        if (paramFlag!=null){
//            paramMap.put("flag",Integer.parseInt(paramFlag));
//        }else {
//            paramMap.put("flag",0);
//        }

//        return paramMap;
        return filterParams;
    }

    // 将"yyyy-mm-dd"修改为"yy年m月份"
    public static String getDateStr(String rawDataStr) {
        StringBuilder builder = new StringBuilder();
        String[] split = rawDataStr.split("-");
        builder.append(split[0].substring(2) + "年");
        if (split[1].startsWith("0")) {

            builder.append(split[1].substring(1) + "月份");
        } else {
            builder.append(split[1] + "月份");

        }

        return builder.toString();
    }

    // 从数据库查找到的和内存中有的找出三个最多的访问次数
    public static ArrayList<Recommend> getMaxList(List<Recommend> recommends, ArrayList<Map.Entry<Integer, Integer>> maps, User user) {

        ArrayList<Recommend> newRecommend = new ArrayList<>();
        ArrayList<Map.Entry<Integer, Integer>> dels = new ArrayList<>();

        for (int i = 0; i < recommends.size(); i++) {
            for (int j = 0; j < maps.size(); j++) {
                if (recommends.get(i).getAreaId().equals(maps.get(j).getKey())){
                    // 如果数据库中已经存在相同的areaId则增加其访问次数
                    recommends.get(i).setTimes(recommends.get(i).getTimes()+maps.get(j).getValue());
                    dels.add(maps.get(j));
                }
            }
        }
        // 把数据库中已经存在的areaId删除
        maps.removeAll(dels);

        maps.forEach(item->{
            Recommend recommend = new Recommend();
            recommend.setUserId(user.getId());
            recommend.setTimes(item.getValue());
            recommend.setAreaId(item.getKey());
            newRecommend.add(recommend);
        });

        newRecommend.addAll(recommends);

        return newRecommend;

    }
}

