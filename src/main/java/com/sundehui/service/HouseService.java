package com.sundehui.service;

import com.sundehui.domain.House;
import com.sundehui.domain.help.FilterParams;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HouseService {

    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //获取分页
//    List<House> getHousePage(Integer page, Integer count, Integer type, Integer flag);
    List<House> getHousePage(FilterParams params);


    // 获取现有可卖房屋的数量
//    int getHouseCount(Integer type);
    int getHouseCount(FilterParams params);


    // 通过用户的id获取该用户发布的所有房源
    List<House> getPublishedByUserId(Integer uId);


    // 通过房屋编号删除房源
    Integer deleteByHouseNumber(String houseNumber);

    // 通过房屋编号将一个房屋标记为已售出
    Integer markHouseSold(String houseNumber);

    Integer getCountForManage(Integer type, Integer examineType, Date today, Integer provinceId, Integer cityId, Integer areaId, String address);

    List<House> getUnchecked(int page, int count);

    int passCheck(Integer hId,Integer examineType);

    List<House> getHousePageForManage(Integer page,Integer count,Integer examineType,
                                      Integer provinceId,Integer cityId,Integer areaId,String address);


    int changeStatus(String houseNumber);
    int changeStatus(Integer id);

    int changeExamineTypeByOwnerId(Integer uId);
}
