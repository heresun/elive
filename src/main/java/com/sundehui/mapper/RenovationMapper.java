package com.sundehui.mapper;

import com.sundehui.domain.ElectricType;
import com.sundehui.domain.Renovation;

import java.util.List;

public interface RenovationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Renovation record);

    int insertSelective(Renovation record);

    Renovation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Renovation record);

    int updateByPrimaryKey(Renovation record);

    List<Renovation> getAll();
}