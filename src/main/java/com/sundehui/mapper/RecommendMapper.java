package com.sundehui.mapper;

import com.sundehui.domain.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);

    List<Recommend> getAll(@Param("uId") Integer uId);
}