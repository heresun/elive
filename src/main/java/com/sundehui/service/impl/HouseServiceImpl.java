package com.sundehui.service.impl;

import com.sundehui.domain.House;
import com.sundehui.mapper.HouseMapper;
import com.sundehui.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(House record) {
        return 0;
    }

    @Override
    public int insertSelective(House record) {
        return 0;
    }

    // 通过主键获取一个房屋的信息
    @Override
    public House selectByPrimaryKey(Integer id) {
        House house = mapper.selectByPrimaryKey(id);
        return house;
    }

    @Override
    public int updateByPrimaryKeySelective(House record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(House record) {
        return 0;
    }
}
