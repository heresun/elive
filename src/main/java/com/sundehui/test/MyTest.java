package com.sundehui.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sundehui.domain.*;
import com.sundehui.domain.Collection;
import com.sundehui.domain.help.DistrictHelp;
import com.sundehui.domain.help.FilterParams;
import com.sundehui.domain.help.TransactionHelper;
import com.sundehui.service.*;
import com.sundehui.util.Utils;
import org.apache.ibatis.type.BlobInputStreamTypeHandler;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean(UserService.class);
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
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionService service = context.getBean(CollectionService.class);

        List<House> allCollection = service.getAllCollection("16650634286");

        allCollection.forEach(System.out::println);
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionService service = context.getBean(CollectionService.class);

        int i = service.deleteByPrimaryKey(2);
        System.out.println(i);
    }


    @Test
    public void test6() {

        String str = "E:\\java\\elive\\target\\elive\\upload\\02439c26-e9da-40c7-a724-97474baeae63.jpg";
        int i1 = str.indexOf("upload");
        String substring = str.substring(i1);
        String[] split = substring.split("\\\\");
        String join = String.join("/", split);
        System.out.println(join);

    }

    @Test
    public void test7() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService service = context.getBean(DistrictService.class);

        District district = service.selectByAreaId(500);

        System.out.println(district);
    }

//    @Test
//    public void test8 (){
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        CollectionService service = context.getBean( CollectionService.class);
//
//        Collection collection = new Collection();
//        collection.setHouseId(1);
//        collection.setUserId(3);
//
//        int i = service.insertSelective(collection);
//
//
//
//        System.out.println(1);
//        System.out.println(collection.getId());
//    }

    @Test
    public void test9() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectionService service = context.getBean(CollectionService.class);

        int i = service.selectCollectedCount(2);
        System.out.println(i);
    }

    @Test
    public void test10() {
        int n = 6;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        n = (n < 0) ? 1 : (n >= 100) ? 100 : n + 1;
        System.out.println(n);
    }

    @Test
    public void test11() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService service = context.getBean(DistrictService.class);

        List<District> districts = service.selectByPid(1);
        System.out.println(districts);

        HashMap<Integer, String> map = new HashMap<>();
        ArrayList<DistrictHelp> districtHelps = new ArrayList<>();

        districts.forEach(item -> {
            districtHelps.add(new DistrictHelp(item.getId(), item.getDistrictName()));
        });


        String string = JSON.toJSONString(districtHelps);
        System.out.println(string);
    }


    @Test
    public void test13() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ImageService service = context.getBean(ImageService.class);

        List<String> imgs = service.selectByHouseNumber("MF45495300206186");

        System.out.println(imgs);
    }

    @Test
    public void test14() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HouseService service = context.getBean(HouseService.class);
        FilterParams params = new FilterParams();
        params.setPage(1);
        params.setCount(10);
//        params.setType(1);

        Integer[] integers = new Integer[2];
        integers[0] = 0;
        integers[1] = 80;

        Integer[] integers2 = new Integer[2];
        integers2[0] = 100;
        integers2[1] = 200;


//        params.setSquare_2(integers);
//        params.setSquare_7(integers2);
//
//        params.setPrice_1(integers);
//        params.setPrice_2(integers2);
//
//        params.setOrien_1(1);
//        params.setOrien_2(2);


        List<House> housePage = service.getHousePage(params);
        int houseCount = service.getHouseCount(params);
        housePage.forEach(item -> {
            System.out.println(item);
        });

        System.out.println("==========数量==========");
        System.out.println(houseCount);
//        paramMap.put("type",1);
//        paramMap.put("provinceId",11);
//        int houseCount = service.getHouseCount(paramMap);
//        System.out.println(houseCount);
    }


    @Test
    public void testPublished() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HouseService service = context.getBean(HouseService.class);

        List<House> publishedByUserId = service.getPublishedByUserId(1);
        System.out.println(publishedByUserId);
    }

    @Test
    public void testSingleLocation() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService service = context.getBean(DistrictService.class);

        District district = service.selectByPrimaryKey(1);
        System.out.println(district);
    }

    @Test
    public void testDeleteOneImg() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ImageService service = context.getBean(ImageService.class);
        String mf41877788814381 = service.getImgRealPath("MF41877788814381", "%236d9218-f3ff-4245-96ce-16315d1e6450.jpg");
        System.out.println(mf41877788814381);
    }

    @Test
    public void teeee() throws IOException {
        boolean b = Files.deleteIfExists(Paths.get("E:\\java\\elive\\target\\elive\\upload\\236d9218-f3ff-4245-96ce-16315d1e6450.jpg"));
        System.out.println(b);
    }

    @Test
    public void testTrnasaction() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service = context.getBean(UserService.class);

        Integer integer = service.getUidByAccount("16650634286");
        System.out.println(integer);
    }
}
