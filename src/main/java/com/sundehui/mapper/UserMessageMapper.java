package com.sundehui.mapper;

import com.sundehui.domain.HouseMessage;
import com.sundehui.domain.User;
import com.sundehui.domain.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);

    UserMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMessage record);

    int updateByPrimaryKey(UserMessage record);

    List<UserMessage> getAll(@Param("uId") Integer uId);
    int changeStatus(@Param("id") Integer id);
    int deleteOne(@Param("id")Integer id);
}