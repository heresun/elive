package com.sundehui.service.impl;

import com.sundehui.domain.Transaction;
import com.sundehui.domain.help.TransactionHelper;
import com.sundehui.mapper.TransactionMapper;
import com.sundehui.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Transaction record) {
        return 0;
    }

    @Override
    public int insertSelective(Transaction record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public Transaction selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Transaction record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Transaction record) {
        return 0;
    }

    // 通过type的不同，查询该用户的交易信息，
    // 如果type==0，则作为卖家，如果type==1,则作为买家
    @Override
    public List<TransactionHelper> getTransaction(Integer uId, Integer type) {
        List<TransactionHelper> transactions=mapper.getTransaction(uId, type);
        return transactions;
    }

    @Override
    public TransactionHelper getTransaction(String houseNumber) {
        TransactionHelper  transactionHelper= mapper.getTransactionByHouseNumber(houseNumber);
        return transactionHelper;
    }

}
