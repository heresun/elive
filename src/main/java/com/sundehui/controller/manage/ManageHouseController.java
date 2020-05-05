package com.sundehui.controller.manage;

import com.alibaba.fastjson.JSON;
import com.sundehui.controller.house.HouseController;
import com.sundehui.domain.District;
import com.sundehui.domain.House;
import com.sundehui.domain.HouseProve;
import com.sundehui.domain.User;
import com.sundehui.service.DistrictService;
import com.sundehui.service.HouseProveService;
import com.sundehui.service.HouseService;
import com.sundehui.service.UserService;
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

    @Autowired
    private HouseProveService houseProveService;

    @Autowired
    private UserService userService;

    @Autowired
    private DistrictService districtService;

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

        List<House> houseList = service.getUnchecked(page, count);
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
        String paramProvinceId = request.getParameter("provinceId");
        String paramCityId = request.getParameter("cityId");
        String paramAreaId = request.getParameter("areaId");
        String paramAddress = request.getParameter("address");
        Integer provinceId = paramProvinceId == null ? null : Integer.parseInt(paramProvinceId);
        Integer cityId = paramCityId == null ? null : Integer.parseInt(paramCityId);
        Integer areaId = paramAreaId == null ? null : Integer.parseInt(paramAreaId);
        Integer type = paramType == null ? null : Integer.parseInt(paramType);
        Integer examineType = paramExamineType == null ? null : Integer.parseInt(paramExamineType);
        Date today = null;

        if (paramToday != null) {
            today = new Date();
        }


        Integer resCount = service.getCountForManage(type, examineType, today, provinceId, cityId, areaId, paramAddress);

        return resCount;
    }

    @GetMapping("/changeExamineType")
    public int passCheck(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        String paramExamineTYpe = request.getParameter("examineType");
        if (paramId == null || paramExamineTYpe == null) {
            return 0;
        }
        int hId = Integer.parseInt(paramId);
        int examineType = Integer.parseInt(paramExamineTYpe);

        int res = service.passCheck(hId, examineType);
        service.changeStatus(hId);
        return res;
    }

    //分页获取不同审核类型的房屋
    @GetMapping(value = "/getHousePage", produces = "text/html;charset=UTF-8")
    public String getHousePage(HttpServletRequest request) {
        String paramPage = request.getParameter("page");
        String paramCount = request.getParameter("count");
        String paramExamineType = request.getParameter("examineType");

        String paramProvinceId = request.getParameter("provinceId");
        String paramCityId = request.getParameter("cityId");
        String paramAreaId = request.getParameter("areaId");
        String paramAddress = request.getParameter("address");

        Integer provinceId = paramProvinceId == null ? null : Integer.parseInt(paramProvinceId);
        Integer cityId = paramCityId == null ? null : Integer.parseInt(paramCityId);
        Integer areaId = paramAreaId == null ? null : Integer.parseInt(paramAreaId);

        if (paramPage == null || paramCount == null || paramExamineType == null) {
            return "err";
        }

        int page = Integer.parseInt(paramPage);
        int count = Integer.parseInt(paramCount);
        int examineType = Integer.parseInt(paramExamineType);

        List<House> houses = service.getHousePageForManage(page, count, examineType, provinceId, cityId, areaId, paramAddress);

        houses.forEach(item -> {
            List<String> all = houseProveService.getAll(item.getHouseNumber());
            User owner = userService.selectByPrimaryKey(item.getOwnerId());
            String province = districtService.selectByPrimaryKey(item.getProvinceId()).getDistrictName();
            String city = districtService.selectByPrimaryKey(item.getCityId()).getDistrictName();
            String area = districtService.selectByPrimaryKey(item.getAreaId()).getDistrictName();


            item.setOwner(owner.getUsername());
            item.setPhone(owner.getPhone());
            item.setProveUrls(all);
            item.setDistrict(province + "/" + city + "/" + area);
        });


        String string = JSON.toJSONString(houses);

        return string;
    }


}
