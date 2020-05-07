package com.sundehui.controller.other;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.District;
import com.sundehui.domain.help.HouseAnalysis;
import com.sundehui.domain.help.LayoutAnalysis;
import com.sundehui.domain.help.PriceAnalysis;
import com.sundehui.domain.help.TransactionAnalysis;
import com.sundehui.mapper.HouseMapper;
import com.sundehui.service.DistrictService;
import com.sundehui.util.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/analysis")
@RestController
public class Aanlysis {

    @Autowired
    private DistrictService service;
    @Autowired
    private HouseMapper houseMapper;


    /**
     * @descriptio 房源分析，即某个市的某个区从一居室到五居室的数量
     */
    @GetMapping(value = "/getLayoutAnalysis", produces = "text/html;charset=UTF-8")
    public String getLayoutAnalysis(HttpServletRequest request) {
        String paramCityId = request.getParameter("cityId");
        if (paramCityId == null) {
            return "err";
        }
        Integer cityId = Integer.parseInt(paramCityId);
        ArrayList<LayoutAnalysis> analyses = new ArrayList<>();

        List<District> districts = service.selectByPid(cityId);


        districts.forEach(item -> {
            LayoutAnalysis layoutAnalysis = new LayoutAnalysis();
            Integer one = houseMapper.getHouseCountForAnalysis(item.getId(), 1);
            Integer two = houseMapper.getHouseCountForAnalysis(item.getId(), 2);
            Integer three = houseMapper.getHouseCountForAnalysis(item.getId(), 3);
            Integer four = houseMapper.getHouseCountForAnalysis(item.getId(), 4);
            Integer five = houseMapper.getHouseCountForAnalysis(item.getId(), 5);
            layoutAnalysis.setArea(item.getDistrictName());
            layoutAnalysis.setOne(one);
            layoutAnalysis.setTwo(two);
            layoutAnalysis.setThree(three);
            layoutAnalysis.setFour(four);
            layoutAnalysis.setFive(five);

            analyses.add(layoutAnalysis);
        });

        String string = JSON.toJSONString(analyses);

        return string;


    }

    /**
     * @descriptio 价格分析，即某个市的某个区的成交价格和发布价格的区别
     */
    @GetMapping(value = "/getPriceAnalysis", produces = "text/html;charset=UTF-8")
    public String getPriceAnalysis(@NotNull HttpServletRequest request) {
        String paramCityId = request.getParameter("cityId");
        if (paramCityId == null) {
            return "err";
        }
        Integer cityId = Integer.parseInt(paramCityId);
        ArrayList<PriceAnalysis> analyses = new ArrayList<>();

        List<District> districts = service.selectByPid(cityId);
        districts.forEach(item -> {
            PriceAnalysis priceAnalysis = new PriceAnalysis();
            // 获取在售的房屋平均价格
            Double pubPrice = houseMapper.selectAvgPrice(item.getId(), 2);
            // 获取已售的房屋平均价格
            Double transPrice = houseMapper.selectAvgPrice(item.getId(), 3);

            if (pubPrice == null || pubPrice == 0d || transPrice == null || transPrice == 0d) {
                priceAnalysis.setPriceDiff(0d);
                priceAnalysis.setTransPrice(0d);
                priceAnalysis.setPubPrice(0d);
                priceAnalysis.setArea(item.getDistrictName());
                analyses.add(priceAnalysis);
            } else {
                Double rate = (pubPrice - transPrice) / pubPrice;
                double diffRate = new BigDecimal(rate).setScale(2, RoundingMode.DOWN).doubleValue();
                priceAnalysis.setPubPrice(pubPrice);
                priceAnalysis.setTransPrice(transPrice);
                priceAnalysis.setPriceDiff(diffRate);
                priceAnalysis.setArea(item.getDistrictName());

                analyses.add(priceAnalysis);
            }

        });

        String string = JSON.toJSONString(analyses);

        return string;

    }


    /**
     * @descriptio 交易量分析，即某个市的某个区的过去十二个月的成交情况
     */
    @GetMapping(value = "/getTransAnalysis", produces = "text/html;charset=UTF-8")
    public String getTransAnalysis(HttpServletRequest request) {
        String paramAreaId = request.getParameter("areaId");
        if (paramAreaId == null) {
            return "err";
        }
        Integer areaId = Integer.parseInt(paramAreaId);
        ArrayList<TransactionAnalysis> analyses = new ArrayList<>();

        LocalDate localDate = LocalDate.now();
        for (long i = 1L; i < 12L; i++) {
            LocalDate preDate = localDate.minusMonths(i);
            Integer count = houseMapper.selectTransAnalysisData(areaId, preDate.toString());
            //创建数据对象
            TransactionAnalysis tsAnalysis = new TransactionAnalysis();
            tsAnalysis.setDataStr(Utils.getDateStr(preDate.toString()));
            tsAnalysis.setTransCount(count);
            // 添加
            analyses.add(tsAnalysis);
        }

        String string = JSON.toJSONString(analyses);
        return string;
    }

    /**
     * @descriptio 交易量分析，即某个市的哪个区的房源更受欢迎
     */
    @GetMapping(value = "/getHouseAnalysis", produces = "text/html;charset=UTF-8")
    public String getHouseAnalysis(HttpServletRequest request) {
        String paramCityId = request.getParameter("cityId");
        if (paramCityId == null) {
            return "err";
        }
        Integer cityId = Integer.parseInt(paramCityId);
        List<District> districts = service.selectByPid(cityId);
        ArrayList<HouseAnalysis> list = new ArrayList<>();

        districts.forEach(item -> {
            HouseAnalysis houseAnalysis = new HouseAnalysis();
            Integer count = houseMapper.selectHouseAnalysisData(item.getId());
            houseAnalysis.setCount(count);
            houseAnalysis.setDistrict(item.getDistrictName());

            list.add(houseAnalysis);
        });

        String string = JSON.toJSONString(list);
        return string;
    }
}
