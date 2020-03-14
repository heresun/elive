package com.sundehui.service;

import com.sundehui.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
