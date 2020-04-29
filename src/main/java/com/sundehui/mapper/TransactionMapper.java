package com.sundehui.mapper;

import com.sundehui.domain.Transaction;
import com.sundehui.domain.help.TransactionHelper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);


    // 通过type的不同，查询该用户的交易信息，
    // 如果type==0，则作为卖家，如果type==1,则作为买家
    List<TransactionHelper> getTransaction(@Param("uId") Integer uId, @Param("type") Integer type);
}