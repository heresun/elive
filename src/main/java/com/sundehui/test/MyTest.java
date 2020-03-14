package com.sundehui.test;

import com.sundehui.domain.User;
import com.sundehui.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        User user = userService.selectByPrimaryKey(1);
        System.out.println(user);
    }
}
