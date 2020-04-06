package com.sundehui.service.impl;

import com.sundehui.domain.Collection;
import com.sundehui.domain.House;
import com.sundehui.mapper.CollectionMapper;
import com.sundehui.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    //通过用户id获取该用户所有收藏的房屋
    @Autowired
    CollectionMapper mapper;

    @Override
    public List<House> getAllCollection(String account) {
        List<House> allCollection = mapper.getAllCollection(account);
        return allCollection;
    }
    // 获取每套房子被收藏的次数
    @Override
    public int selectCollectedCount(Integer id) {
        int i = mapper.selectCollectedCount(id);
        return i;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);

        return i;
    }

    // 插入一条收藏记录
    @Override
    public int insert(Collection record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public int insertSelective(Collection record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public Collection selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Collection record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Collection record) {
        return 0;
    }
}
