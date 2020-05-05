package com.sundehui.mapper;

import com.sundehui.domain.Transaction;
import com.sundehui.domain.help.TransactionHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    List<TransactionHelper> getTransaction(@Param("uId") Integer uId, @Param("type") Integer type);

    TransactionHelper getTransactionByHouseNumber(@Param("houseNumber") String houseNumber);

}