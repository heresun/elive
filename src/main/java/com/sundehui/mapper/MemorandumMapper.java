package com.sundehui.mapper;

import com.sundehui.domain.Memorandum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemorandumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Memorandum record);

    int insertSelective(Memorandum record);

    Memorandum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Memorandum record);

    int updateByPrimaryKey(Memorandum record);

    int changeStatus(@Param("id") Integer id,@Param("status") Integer status);

    List<Memorandum> getAll(@Param("uId") Integer uId);
}