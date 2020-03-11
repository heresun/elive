package com.sundehui.dao;

import com.sundehui.domain.Jurisdiction;

public interface JurisdictionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Jurisdiction record);

    int insertSelective(Jurisdiction record);
}