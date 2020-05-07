package com.sundehui.service.impl;

import com.sundehui.domain.HouseProve;
import com.sundehui.domain.UserProve;
import com.sundehui.mapper.UserProveMapper;
import com.sundehui.service.UserProveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProveServiceImpl implements UserProveService {

    @Autowired
    private UserProveMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserProve record) {
        return 0;
    }

    @Override
    public int insertSelective(UserProve record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public UserProve selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserProve record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserProve record) {
        return 0;
    }

    @Override
    public List<UserProve> selectByUserId(Integer uId) {
        List<UserProve> houseProves = mapper.selectByUserId(uId);
        return houseProves;
    }

    @Override
    public int deleteOneProve(String imageName, Integer uId) {
        String img = "%"+imageName+"%";
        int i = mapper.deleteOneProve(img,uId);

        return i;
    }

}
