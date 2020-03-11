package com.sundehui.service.impl;

import com.sundehui.dao.IAccountDao;
import com.sundehui.domain.Account;
import com.sundehui.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private IAccountDao iAccountDao;

    public AccountServiceImpl(IAccountDao iAccountDao) {
        this.iAccountDao = iAccountDao;
    }

    @Override
    public List<Account> findAll() {
        List<Account> all = iAccountDao.findAll();
        return all;
    }

    @Override
    public void saveAccount(Account account) {
        iAccountDao.saveAccount(account);
    }
}
