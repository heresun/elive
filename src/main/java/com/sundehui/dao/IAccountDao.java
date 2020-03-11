package com.sundehui.dao;

import com.sundehui.domain.Account;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IAccountDao {
//     @Select("select * from account")
     List<Account> findAll();
//     @Insert("insert into account (name,money) value(#{name},#{money})")
     void saveAccount(Account account);
}
