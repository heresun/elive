package com.sundehui.controller.manage;


import com.alibaba.fastjson.JSON;
import com.sundehui.domain.Memorandum;
import com.sundehui.service.MemorandumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/manage/memorandum")
public class MemorandumController {

    @Autowired
    private MemorandumService service;

    @GetMapping(value = "/getAll", produces = "text/html;charset=UTF-8")
    public String getAll(HttpServletRequest request) {

        String paramUId = request.getParameter("uId");
        if (paramUId == null) {
            return null;
        }

        int uId = Integer.parseInt(paramUId);
        List<Memorandum> all = service.getAll(uId);
        String string = JSON.toJSONString(all);

        return string;
    }

    @GetMapping("/changeStatus")
    public int changeStatus(HttpServletRequest request) {
        String paramStatus = request.getParameter("status");
        String paramId = request.getParameter("id");
        if (paramStatus == null || paramId == null) {
            return 0;
        }

        int status = Integer.parseInt(paramStatus);
        int id = Integer.parseInt(paramId);

        int i = service.changeStatus(id, status);
        return i;

    }

    @PostMapping("/addOne")
    public String addOne(HttpServletRequest request) {
        String paramContent = request.getParameter("content");
        String paramUId = request.getParameter("uId");
        if (paramContent == null || paramUId == null) {
            return "err";
        }

        int uId = Integer.parseInt(paramUId);

        Memorandum memorandum = new Memorandum();
        memorandum.setContent(paramContent);
        memorandum.setUserId(uId);

        int i = service.addOne(memorandum);
        if (i > 0) {
            return "ok";
        }

        return "err";
    }

    @GetMapping("/deleteOne")
    public int deleteOne(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        if (paramId == null) {
            return 0;
        }

        int id = Integer.parseInt(paramId);

        int i = service.deleteOne(id);
        return i;
    }
}
