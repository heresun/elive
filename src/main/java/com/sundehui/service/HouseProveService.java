package com.sundehui.service;

import com.sundehui.domain.HouseProve;

import java.util.List;

public interface HouseProveService {

    int deleteByPrimaryKey(Integer id);

    int insert(HouseProve record);

    int insertSelective(HouseProve record);

    HouseProve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseProve record);

    int updateByPrimaryKey(HouseProve record);

    List<String> getAll(String houseNumber);

    int deleteOne(String houseNumber, String imageName);
}
