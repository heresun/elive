package com.sundehui.mapper;

import com.sundehui.domain.House;
import com.sundehui.domain.help.FilterParams;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    // 获取分页， type区分二手房和新房
//    List<House>  getHousePage(@Param("from") Integer from,@Param("count")Integer count, @Param("type")Integer type);
    List<House> getHousePage(@Param("params") FilterParams params);

    // 获取二手房或者新房的数量
//    int getHouseCount(@Param("type")Integer type);
    int getHouseCount(@Param("params") FilterParams params);

    // 获取最新发布
    List<House> getHousePageNewest(@Param("from") Integer from, @Param("count") Integer count, @Param("type") Integer type);

    // 按价格升序排列
    List<House> getHousePageByPriceAsce(@Param("from") Integer from, @Param("count") Integer count, @Param("type") Integer type);

    // 按面积升序排列
    List<House> getHousePageByAreaSizeAsce(@Param("from") Integer from, @Param("count") Integer count, @Param("type") Integer type);

    // 通过用户的id获取该用户发布的所有房源
    List<House> getPublishedByUserId(@Param("uId") Integer uId);

    // 通过房屋编号删除房源
    Integer deleteByHouseNumber(@Param("houseNumber") String houseNumber);

    // 通过房屋编号将一个房屋标记为已售出
    Integer markHouseSold(@Param("houseNumber") String houseNumber);

    Integer getCountForManage(@Param("type") int type,@Param("examineType") int examineType, @Param("today") Date today);

    List<House> getUnchecked(@Param("from") int from,@Param("count") int count);

    int passCheck(@Param("id") int hId, @Param("examineType") int examineType);
}