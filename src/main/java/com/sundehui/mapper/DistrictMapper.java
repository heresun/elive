package com.sundehui.mapper;

import com.sundehui.domain.District;

import java.util.List;

public interface DistrictMapper {

    // 根据房屋的areaId查找房屋所在的地区
    District selectByAreaId(Integer id);

    List<District> selectByPid(Integer argId);

    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}