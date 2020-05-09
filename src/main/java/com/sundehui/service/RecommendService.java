package com.sundehui.service;

import com.sundehui.domain.Recommend;

import java.util.List;

public interface RecommendService {


    List<Recommend> getAll(Integer uId);

    int deleteByPrimaryKey(Integer id);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);

}
