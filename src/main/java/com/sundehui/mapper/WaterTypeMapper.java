package com.sundehui.mapper;

import com.sundehui.domain.ElectricType;
import com.sundehui.domain.WaterType;

import java.util.List;

public interface WaterTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaterType record);

    int insertSelective(WaterType record);

    WaterType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaterType record);

    int updateByPrimaryKey(WaterType record);

    List<WaterType> getAll();
}