package com.sundehui.service;

import com.sundehui.domain.HouseMessage;

import java.util.List;

public interface HouseMessageService {

    List<HouseMessage> getAll(Integer id);

    int deleteOne(Integer id);
    int changeStatus(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(HouseMessage record);

    int insertSelective(HouseMessage record);

    HouseMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseMessage record);

    int updateByPrimaryKey(HouseMessage record);


}
