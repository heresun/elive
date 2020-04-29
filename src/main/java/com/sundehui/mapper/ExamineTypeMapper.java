package com.sundehui.mapper;

import com.sundehui.domain.ExamineType;

public interface ExamineTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamineType record);

    int insertSelective(ExamineType record);

    ExamineType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamineType record);

    int updateByPrimaryKey(ExamineType record);
}