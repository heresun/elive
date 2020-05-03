package com.sundehui.controller.manage;

import com.alibaba.fastjson.JSON;
import com.sundehui.controller.house.HouseController;
import com.sundehui.domain.House;
import com.sundehui.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manage/house")
public class ManageHouseController {

    @Autowired
    private HouseController houseController;

    @Autowired
    private HouseService service;

    // 根据房屋id获取房屋全部信息
    @GetMapping(value = "/getOne", produces = "text/html;charset=UTF-8")
    public String finOne(HttpServletRequest request) {
        String one = houseController.findOne(request);
        return one;
    }

    // 分页获取待审核的房屋信息
    @GetMapping(value = "/getUnCheck", produces = "text/html;charset=UTF-8")
    public String getUnChecked(HttpServletRequest request) {

        String paramPage = request.getParameter("page");
        String paramCount = request.getParameter("count");
        if (paramPage == null || paramCount == null) {
            return null;
        }

        int page = Integer.parseInt(paramPage);
        int count = Integer.parseInt(paramCount);

        List<House> houseList = service.getUnchecked(page,count);
        String houses = JSON.toJSONString(houseList);
        return houses;
    }

    // 获取各种房屋数量
    @GetMapping("/getCount")
    public int getCount(HttpServletRequest request) {

        //二手房 1， 新房 0
        String paramType = request.getParameter("type");
        String paramExamineType = request.getParameter("examineType");
        String paramToday = request.getParameter("today");
        Date today = null;

        if (paramType == null || paramExamineType == null) {
            return 0;
        }
        if (paramToday != null) {
            today = new Date();
        }
        int type = Integer.parseInt(paramType);
        int examineType = Integer.parseInt(paramExamineType);

        Integer resCount = service.getCountForManage(type, examineType, today);

        return resCount;
    }

    @GetMapping("/changeExamineTye")
    public int passCheck ( HttpServletRequest request){
        String paramId = request.getParameter("id");
        String paramExamineTYpe = request.getParameter("examineTYpe");
        if (paramId == null || paramExamineTYpe == null){
            return 0;
        }
        int hId = Integer.parseInt(paramId);
        int examineType = Integer.parseInt(paramExamineTYpe);
        int res = service.passCheck(hId, examineType);
        return res;
    }

}
