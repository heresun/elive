package com.sundehui.service.impl;

import com.sundehui.domain.UserProve;
import com.sundehui.mapper.UserMapper;
import com.sundehui.domain.User;
import com.sundehui.mapper.UserProveMapper;
import com.sundehui.service.UserProveService;
import com.sundehui.service.UserService;
import com.sundehui.util.ImgUtil;
import org.jetbrains.annotations.NotNull;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.InsertElementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserProveMapper userProveMapper;


    @Override
    public List<User> findAll(Integer page, Integer count) {
        Integer from = (page - 1) * count;

        List<User> all = mapper.findAll(from, count);
        all.forEach(item -> {
            String temp = ImgUtil.realPathToUrl(request, item.getPhoto());
            item.setPhoto(temp);
        });
        return all;
    }

    @Override
    public List<User> findAll(Integer page, Integer count, Integer examineType,String account) {
        Integer from = (page - 1) * count;
        if (account!=null){
            account = "%"+account+"%";
        }
        List<User> allByExamineType = mapper.findAllByExamineType(from, count, examineType,account);

        return getUsers(allByExamineType);
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
        int i = mapper.updateByPrimaryKeySelective(record);
        return i;
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
    public List<User> findLikeByAccount(String account, Integer page, Integer count, Integer examineType) {
        String accountLike = "%" + account + "%";
        Integer from = (page - 1) * count;

        List<User> users = mapper.findLikeByAccount(accountLike, from, count, examineType);
        return getUsers(users);
    }

    @NotNull
    private List<User> getUsers(List<User> users) {
        users.forEach(item -> {
            if (item != null) {


                String temp = ImgUtil.realPathToUrl(request, item.getPhoto());
                List<String> proveUrls = new ArrayList<>();
                item.setPhoto(temp);
                List<UserProve> userProves = userProveMapper.selectByUserId(item.getId());
                userProves.forEach(unit->{
                    if (unit!=null){

                        proveUrls.add(unit.getUri());
                    }
                });
                List<String> list = ImgUtil.realPathToUrl(request, proveUrls);
                item.setProveUrls(list);
            }
        });
        return users;
    }

    @Override
    public Integer getUserCount(Integer examineType,String account) {
        if (account!=null){
            account = "%"+account+"%";
        }
        System.out.println("adfadfadfa=============adfadfa=====================");
        System.out.println(account);
        Integer resCount = mapper.getUserCount(examineType,account);
        return resCount;
    }

    @Override
    public int passCheck(int uId, int examineType) {

        int res = mapper.changeExamineType(uId, examineType);
        return res;
    }

    @Override
    public int  changeStatus(int uId) {
        int i = mapper.changeStatus(uId);
        return i;
    }
}
