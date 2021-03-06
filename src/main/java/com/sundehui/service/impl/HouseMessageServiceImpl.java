package com.sundehui.service.impl;

import com.sundehui.domain.HouseMessage;
import com.sundehui.mapper.HouseMessageMapper;
import com.sundehui.service.HouseMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseMessageServiceImpl implements HouseMessageService {

    @Autowired
    private HouseMessageMapper mapper;

    @Override
    public List<HouseMessage> getAll(Integer id) {
        List<HouseMessage> messages =  mapper.getAll(id);
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
    public int insert(HouseMessage record) {
        return 0;
    }

    @Override
    public int insertSelective(HouseMessage record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public HouseMessage selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(HouseMessage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(HouseMessage record) {
        return 0;
    }
}
