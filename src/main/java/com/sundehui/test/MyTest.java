package com.sundehui.test;

import com.alibaba.fastjson.JSON;
import com.sundehui.domain.Collection;
import com.sundehui.domain.District;
import com.sundehui.domain.House;
import com.sundehui.domain.User;
import com.sundehui.service.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class MyTest {
    @Test
    public void test (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean( UserService.class);
//        User user1 = new User(
//                "1234567",
//                "sundehui0000",
//                "大龙虾",
//                "大龙虾市",
//                "134",
//                "E:\\java\\elive\\target\\elive\\upload\\791fa97a-a87e-4adf-8231-e2e77175facc.jpeg");
//        int i = userService.insertSelective(user1);

        // 测试通过主键查询
        User user = userService.selectByPrimaryKey(1);
        //测试通过账户联合查询
        User user1 = userService.selectByAccount("17725344281");
        String string = JSON.toJSONString(user);
        User user2 = JSON.parseObject(string, User.class);
        System.out.println(user1);

    }

    @Test
    public void test2 (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionService service = context.getBean( CollectionService.class);

        List<House> allCollection = service.getAllCollection("16650634286");

        allCollection.forEach(System.out::println);
    }

    @Test
    public void test3 (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionService service = context.getBean( CollectionService.class);

        int i = service.deleteByPrimaryKey(2);
        System.out.println(i);
    }

    @Test
    public void test4 (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HouseService service = context.getBean( HouseService.class);

        House house = service.selectByPrimaryKey(1);
        System.out.println(house);
    }

    @Test
    public void test5 (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ImgService service = context.getBean( ImgService.class);

        List<String> uris = service.findByHouseId(1);

        System.out.println(uris);
    }

    @Test
    public void test6 (){

        String str = "E:\\java\\elive\\target\\elive\\upload\\02439c26-e9da-40c7-a724-97474baeae63.jpg";
        int i1 = str.indexOf("upload");
        String substring = str.substring(i1);
        String[] split = substring.split("\\\\");
        String join = String.join("/", split);
        System.out.println(join);

    }

    @Test
    public void test7 (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService service = context.getBean( DistrictService.class);

        District district = service.selectByAreaId(500);

        System.out.println(district);
    }

    @Test
    public void test8 (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionService service = context.getBean( CollectionService.class);

        Collection collection = new Collection();
        collection.setHouseId(1);
        collection.setUserId(3);

        int i = service.insertSelective(collection);



        System.out.println(1);
        System.out.println(collection.getId());
    }

    @Test
    public void test9 (){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionService service = context.getBean( CollectionService.class);

        int i = service.selectCollectedCount(1);
        System.out.println(i);
    }
    @Test
    public void test10 (){
        int n = 6;
        n |=n >>> 1;
        n |=n >>> 2;
        n |=n >>> 4;
        n |=n >>> 8;
        n |=n >>> 16;

        n = (n<0)? 1 : (n>=100) ? 100: n+1;
        System.out.println(n);
    }
}
