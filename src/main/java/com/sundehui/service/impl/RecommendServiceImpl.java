package com.sundehui.service.impl;

import com.sundehui.domain.Recommend;
import com.sundehui.mapper.RecommendMapper;
import com.sundehui.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendMapper mapper;

    @Override
    public List<Recommend> getAll(Integer uId) {
        List<Recommend> recommends = mapper.getAll(uId);
        return recommends;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Recommend record) {
        return 0;
    }

    @Override
    public int insertSelective(Recommend record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public Recommend selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Recommend record) {
        int i = mapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public int updateByPrimaryKey(Recommend record) {
        return 0;
    }
}
