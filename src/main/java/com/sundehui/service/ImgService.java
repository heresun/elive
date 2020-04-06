package com.sundehui.service;

import com.sundehui.domain.Img;

import java.util.List;

public interface ImgService {

    // 通过house的id找到某套房子的全部图片
    List<String> findByHouseId(int id);

    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
}
