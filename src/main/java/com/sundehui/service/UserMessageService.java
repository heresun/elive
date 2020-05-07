package com.sundehui.service;

import com.sundehui.domain.HouseMessage;
import com.sundehui.domain.UserMessage;

import java.util.List;

public interface UserMessageService {

    List<UserMessage> getAll(Integer uId);

    int deleteOne(Integer id);
    int changeStatus(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);

    UserMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMessage record);

    int updateByPrimaryKey(UserMessage record);
}
