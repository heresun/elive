package com.sundehui.mapper;

import com.sundehui.domain.Jurisdiction;

public interface JurisdictionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Jurisdiction record);

    int insertSelective(Jurisdiction record);
}