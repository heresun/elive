package com.sundehui.service.impl;

import com.sundehui.domain.Img;
import com.sundehui.mapper.ImgMapper;
import com.sundehui.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {

    @Autowired
    private ImgMapper mapper;

    @Override
    public List<String> findByHouseId(int id) {
        List<String> uris = mapper.findByHouseId(id);

        return uris;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Img record) {
        return 0;
    }

    @Override
    public int insertSelective(Img record) {
        return 0;
    }

    @Override
    public Img selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Img record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Img record) {
        return 0;
    }
}
