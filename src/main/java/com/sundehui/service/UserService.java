package com.sundehui.service;

import com.sundehui.domain.User;

import java.util.List;

public interface UserService {

    //获取所有用户
    List<User> findAll();

    //通过主键删除用户
    int deleteByPrimaryKey(Integer id);

    //增加一个用户、
    int insert(User record);

    //添加用户的一部分字段
    int insertSelective(User record);

    //通过用户主键获取一个用户
    User selectByPrimaryKey(Integer id);

    //通过用户账号，获取一个用户
    User selectByAccount(String account);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
