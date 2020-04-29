package com.sundehui.service;

import com.sundehui.domain.District;
import com.sundehui.domain.House;

import java.util.List;

public interface DistrictService {

    // 根据房屋的areaId查找房屋所在的地区
    District selectByAreaId(Integer id);

    // 根据pid查找地区
    List<District> selectByPid(Integer argId);


    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}
