package com.sundehui.mapper;

import com.sundehui.domain.HouseProve;
import com.sundehui.domain.UserProve;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserProve record);

    int insertSelective(UserProve record);

    UserProve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserProve record);

    int updateByPrimaryKey(UserProve record);

    List<UserProve> selectByUserId(@Param("uId") Integer uId);

    int deleteOneProve(@Param("imageName") String imageName,@Param("uId") Integer uId);
}