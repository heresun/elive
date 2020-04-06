package com.sundehui.mapper;

import com.sundehui.domain.House;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
}