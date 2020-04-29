package com.sundehui.service;

import com.sundehui.domain.Transaction;
import com.sundehui.domain.help.TransactionHelper;

import java.util.List;

public interface TransactionService {

    int deleteByPrimaryKey(Integer id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);


    // 通过type的不同，查询该用户的交易信息，
    // 如果type==0，则作为卖家，如果type==1,则作为买家
    List<TransactionHelper> getTransaction(Integer uId, Integer type);
}
