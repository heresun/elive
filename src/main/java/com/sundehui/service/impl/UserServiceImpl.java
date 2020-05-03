package com.sundehui.service.impl;

import com.sundehui.mapper.UserMapper;
import com.sundehui.domain.User;
import com.sundehui.service.UserService;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.InsertElementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;


    @Override
    public List<User> findAll(Integer page, Integer count) {
        Integer from = (page - 1) * count;

        List<User> all = mapper.findAll(from, count);
        return all;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        User user = mapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public User selectByAccount(String account) {
        User user = mapper.selectByAccount(account);
        return user;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public Integer checkAccount(String account) {
        Integer i = mapper.checkAccount(account);
        return i;
    }

    @Override
    public Integer getUidByAccount(String buyerAccount) {
        Integer uId = mapper.getUidByAccount(buyerAccount);
        return uId;
    }

    @Override
    public List<User> findLikeByAccount(String account, Integer page, Integer count) {
        String accountLike = "%" + account + "%";
        Integer from = (page - 1) * count;

        List<User> users = mapper.findLikeByAccount(accountLike, from, count);
        return users;
    }

    @Override
    public Integer getUserCount(Integer i) {
        Integer resCount = mapper.getUserCount(i);
        return resCount;
    }

    @Override
    public int passCheck(int uId,int examineType) {

        int res = mapper.passCheck(uId,examineType);
        return res;
    }
}
