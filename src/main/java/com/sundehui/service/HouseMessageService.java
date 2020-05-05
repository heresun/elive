package com.sundehui.service;

import com.sundehui.domain.HouseMessage;

public interface HouseMessageService {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseMessage record);

    int insertSelective(HouseMessage record);

    HouseMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseMessage record);

    int updateByPrimaryKey(HouseMessage record);
}
