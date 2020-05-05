package com.sundehui.service.impl;

import com.sundehui.domain.UserMessage;
import com.sundehui.mapper.UserMessageMapper;
import com.sundehui.mapper.UserProveMapper;
import com.sundehui.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageServiceImpl implements UserMessageService {


    @Autowired
    private UserMessageMapper mapper;

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
