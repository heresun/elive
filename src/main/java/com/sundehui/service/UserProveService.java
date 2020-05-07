package com.sundehui.service;

import com.sundehui.domain.HouseProve;
import com.sundehui.domain.UserProve;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProveService {

    int deleteByPrimaryKey(Integer id);

    int insert(UserProve record);

    int insertSelective(UserProve record);

    UserProve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserProve record);

    int updateByPrimaryKey(UserProve record);

    List<UserProve> selectByUserId( Integer uId);


    int deleteOneProve(String imageName, Integer uId);
}
