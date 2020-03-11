package com.sundehui.test;


import com.sundehui.domain.Account;
import com.sundehui.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void run1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Account account = (Account) context.getBean("account");
        System.out.println(account);
    }
}
