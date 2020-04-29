package com.sundehui.mapper;

import com.sundehui.domain.ElectricType;
import com.sundehui.domain.Orientation;

import java.util.List;

public interface OrientationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orientation record);

    int insertSelective(Orientation record);

    Orientation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orientation record);

    int updateByPrimaryKey(Orientation record);

    List<Orientation> getAll();
}