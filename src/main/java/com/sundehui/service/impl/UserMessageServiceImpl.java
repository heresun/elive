package com.sundehui.service.impl;

import com.sundehui.domain.HouseMessage;
import com.sundehui.domain.UserMessage;
import com.sundehui.mapper.UserMessageMapper;
import com.sundehui.mapper.UserProveMapper;
import com.sundehui.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageMapper mapper;

    @Override
    public List<UserMessage> getAll(Integer uId) {
        List<UserMessage> messages =  mapper.getAll(uId);
        return messages;
    }

    @Override
    public int deleteOne(Integer id) {
        int i = mapper.deleteOne(id);

        return i;
    }

    @Override
    public int changeStatus(Integer id) {
        int i = mapper.changeStatus(id);
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(UserMessage record) {
        return 0;
    }

    @Override
    public int insertSelective(UserMessage record) {
        int i = mapper.insertSelective(record);

        return i;
    }

    @Override
    public UserMessage selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UserMessage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserMessage record) {
        return 0;
    }
}
