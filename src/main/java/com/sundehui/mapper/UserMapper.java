package com.sundehui.mapper;

import com.sundehui.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> findAll(@Param("from") Integer from, @Param("count") Integer count);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByAccount(String account);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Integer checkAccount(@Param("account") String account);

    Integer getUidByAccount(@Param("account") String buyerAccount);

    List<User> findLikeByAccount(@Param("accountLike") String accountLike, @Param("from") Integer from, @Param("count") Integer count);

    // type==0：查询未通过审核的用户数量，type==1：查询通过审核的用户数量，type==2：查询用户总数
    Integer getUserCount(@Param("type") Integer type);

    int passCheck(@Param("id")  int uId, @Param("examineType") int examineType);
}