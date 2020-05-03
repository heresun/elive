package com.sundehui.mapper;

import com.sundehui.domain.UserProve;

public interface UserProveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserProve record);

    int insertSelective(UserProve record);

    UserProve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserProve record);

    int updateByPrimaryKey(UserProve record);
}