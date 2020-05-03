package com.sundehui.mapper;

import com.sundehui.domain.HouseProve;

public interface HouseProveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseProve record);

    int insertSelective(HouseProve record);

    HouseProve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseProve record);

    int updateByPrimaryKey(HouseProve record);
}