package com.sundehui.test;

import com.sundehui.dao.IAccountDao;
import com.sundehui.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    @Test
    public void run1 () throws IOException {
        Account account = new Account();
        account.setName("孙德辉");
        account.setMoney(2000d);
        //加载配置文件
        InputStream rs = Resources.getResourceAsStream("mybatisConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(rs);
        //创建SqlSession对象
        SqlSession session = factory.openSession();
        //动态代理获取对象
        IAccountDao mapper = session.getMapper(IAccountDao.class);
        mapper.saveAccount(account);
        //提交事务
        session.commit();
        //关闭资源
        session.close();
        rs.close();
    }

    @Test
    public void run2 () throws IOException {
        InputStream rs = Resources.getResourceAsStream("mybatisConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(rs);
        SqlSession session = factory.openSession();
        IAccountDao mapper = session.getMapper(IAccountDao.class);
        List<Account> all = mapper.findAll();
        all.forEach(item->{
            System.out.println(item);
        });
        session.close();
        rs.close();
    }
}
