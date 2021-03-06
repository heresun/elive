package com.sundehui.mapper;

import com.sundehui.domain.HouseProve;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseProveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseProve record);

    int insertSelective(HouseProve record);

    HouseProve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseProve record);

    int updateByPrimaryKey(HouseProve record);

    List<HouseProve> getAll(@Param("houseNumber") String houseNumber);

    int deleteOne(@Param("houseNumber") String houseNumber,@Param("imageName") String imageName);
}