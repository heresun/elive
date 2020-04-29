package com.sundehui.mapper;

import com.sundehui.domain.ElectricType;

import java.util.List;

public interface ElectricTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElectricType record);

    int insertSelective(ElectricType record);

    ElectricType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElectricType record);

    int updateByPrimaryKey(ElectricType record);

    List<ElectricType> getAll();
}