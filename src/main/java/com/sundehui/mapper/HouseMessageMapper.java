package com.sundehui.mapper;

import com.sundehui.domain.HouseMessage;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface HouseMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseMessage record);

    int insertSelective(HouseMessage record);

    HouseMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseMessage record);

    int updateByPrimaryKey(HouseMessage record);

    List<HouseMessage> getAll(@Param("uId") Integer uId);
    int changeStatus(@Param("id") Integer id);
    int deleteOne(@Param("id")Integer id);
}