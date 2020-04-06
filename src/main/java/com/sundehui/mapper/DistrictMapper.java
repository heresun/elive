package com.sundehui.mapper;

import com.sundehui.domain.District;

public interface DistrictMapper {

    // 根据房屋的areaId查找房屋所在的地区
    District selectByAreaId(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}