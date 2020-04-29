package com.sundehui.mapper;

import com.sundehui.domain.ElectricType;
import com.sundehui.domain.HeatingType;

import java.util.List;

public interface HeatingTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatingType record);

    int insertSelective(HeatingType record);

    HeatingType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatingType record);

    int updateByPrimaryKey(HeatingType record);

    List<HeatingType> getAll();
}