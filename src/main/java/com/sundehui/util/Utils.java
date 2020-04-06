package com.sundehui.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
        list.forEach(item->{
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


    private static  Callable<String> getRun(MultipartFile file, HttpServletRequest request) {
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
                try(
                        OutputStream os = new FileOutputStream(fileName);
                        InputStream is = file.getInputStream();
                ) {
                    //定义缓冲区大小
                    byte[] buffer = new byte[1024];

                    //声明从输入流一次获取的数据长度
                    int len = 0;
                    //输出
                    while((len = is.read(buffer))>0){
                        os.write(buffer, 0, len);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return fileName;
        };
    }
}