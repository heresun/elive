package com.sundehui.service.impl;

import com.sundehui.domain.*;
import com.sundehui.domain.help.FilterParams;
import com.sundehui.mapper.*;
import com.sundehui.service.HouseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class HouseServiceImpl implements HouseService {


    @Autowired
    private HouseMapper mapper;

    @Autowired
    private ExamineTypeMapper examineTypeMapper;

    @Autowired
    private ElectricTypeMapper electricTypeMapper;

    @Autowired
    private WaterTypeMapper waterTypeMapper;

    @Autowired
    private OrientationMapper orientationMapper;

    @Autowired
    private HeatingTypeMapper heatingTypeMapper;

    @Autowired
    private RenovationMapper renovationMapper;

    @Autowired
    private ImageMapper imageMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int insert(House record) {
        return 0;
    }

    @Override
    public int insertSelective(House record) {
        int i = mapper.insertSelective(record);

        return i;
    }

    @Override
    public House selectByPrimaryKey(Integer id) {

        House houseSale = mapper.selectByPrimaryKey(id);
        if (houseSale == null) {
            return houseSale;
        }
        // 获取各种辅助字段
        Integer electricType = houseSale.getElectricType();
        Integer waterType = houseSale.getWaterType();
        String houseNumber = houseSale.getHouseNumber();
        Integer ownerId = houseSale.getOwnerId();
        Integer orientation = houseSale.getOrientation();
        Integer heatingType = houseSale.getHeatingType();
        Integer renovationType = houseSale.getRenovationType();

        // 查询各个字段
        ElectricType electric = electricTypeMapper.selectByPrimaryKey(electricType);
        WaterType water = waterTypeMapper.selectByPrimaryKey(waterType);
        List<Image> images = imageMapper.selectByHouseNumber(houseNumber);
        Orientation orientation1 = orientationMapper.selectByPrimaryKey(orientation);
        HeatingType heating = heatingTypeMapper.selectByPrimaryKey(heatingType);
        Renovation renovation = renovationMapper.selectByPrimaryKey(renovationType);


        if (electric != null) {
            houseSale.setElectric(electric.getName());
        }
        if (water != null) {

            houseSale.setWater(water.getName());
        }
        if (images != null && images.size() > 0) {

            houseSale.setUrl(images.get(0).getUri());
        }
        if (orientation1 != null) {

            houseSale.setOrientedDirection(orientation1.getDirection());
        }
        if (heating != null) {

            houseSale.setHeating(heating.getName());
        }
        if (renovation != null) {

            houseSale.setRenovation(renovation.getType());
        }


        return houseSale;
    }

    @Override
    public int updateByPrimaryKeySelective(House record) {
        int i = mapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public int updateByPrimaryKey(House record) {
        return 0;
    }

    @Override
    public List<House> getHousePage(FilterParams param) {

        // 将address改为模糊查询参数
        String address = param.getAddress();
        if (address != null && address.length() > 0) {
            String addressLikeStr = String.format("%s%s%s", "%", address, "%");
            param.setAddress(addressLikeStr);
        }

        Integer paramPage = param.getPage();
        Integer paramCount = param.getCount();
        if (paramPage != null && paramCount != null) {
            Integer from = (paramPage - 1) * paramCount;
            param.setPage(from);
        }

        List<House> housePage = mapper.getHousePage(param);
        // 获取房屋的图片uri
        if (housePage != null && housePage.size() > 0) {
            housePage.forEach(item -> {
                List<Image> images = imageMapper.selectByHouseNumber(item.getHouseNumber());
                if (images != null && images.size() > 0) {
                    String uri = images.get(0).getUri();
                    item.setUrl(uri);
                }
            });
        }

        return housePage;
    }

//    @Override
//    public List<House> getHousePage(Integer page, Integer count, Integer type, Integer flag) {
//        Integer from = (page-1)*count;
//        List<House> housePage =  null;
//
//        switch (flag){
//            case 0:
//                housePage = mapper.getHousePage(from, count, type);
//                break;
//            case 1:// 按最新发布排序
//                housePage = mapper.getHousePageNewest(from, count, type);
//                housePage.sort((item1, item2)->
//                        (int) (item2.getPubDate().getTime() - item1.getPubDate().getTime()));
//                break;
//            case 2: // 按价格升序排列
//                housePage = mapper.getHousePageByPriceAsce(from, count, type);
//                housePage.sort((item1, item2)->
//                        (int) (item1.getPrice() - item2.getPrice()));
//                break;
//            case 3: // 按面积升序排列
//                housePage = mapper.getHousePageByAreaSizeAsce(from, count, type);
//                housePage.sort((item1, item2)->
//                        (int) (item1.getAreaSize() - item2.getAreaSize()));
//                break;
//            default:
//                housePage = mapper.getHousePage(from, count, type);
//                break;
//
//        }
//
//
//        if (housePage!=null && housePage.size()>0){
//            housePage.forEach(item->{
//                List<Image> images = imageMapper.selectByHouseNumber(item.getHouseNumber());
//                if (images!=null && images.size()>0){
//                    String uri = images.get(0).getUri();
//                    item.setUrl(uri);
//                }
//            });
//        }
//
//
//        return housePage;
//    }

//    @Override
//    public int getHouseCount(Integer type) {
//
//        int houseCount = mapper.getHouseCount(type);
//
//        return houseCount;
//    }


    @Override
    public int getHouseCount(FilterParams params) {
        int houseCount = mapper.getHouseCount(params);
        return houseCount;
    }

    @Override
    public List<House> getPublishedByUserId(Integer uId) {
        List<House> houses = mapper.getPublishedByUserId(uId);

        return houses;
    }

    @Override
    public Integer deleteByHouseNumber(String houseNumber) {
        Integer i = mapper.deleteByHouseNumber(houseNumber);
        return i;
    }

    @Override
    public Integer markHouseSold(String houseNumber) {
        Integer i = mapper.markHouseSold(houseNumber);
        return i;
    }

    @Override
    public Integer getCountForManage(int type, int examineType, Date today) {

        Integer resCount = mapper.getCountForManage(type, examineType, today);

        return resCount;
    }

    @Override
    public List<House> getUnchecked(int page, int count) {

        int from = (page - 1) * count;

        List<House> houses = mapper.getUnchecked(from, count);
        return null;
    }

    @Override
    public int passCheck(int hId, int examineType) {
        int res = mapper.passCheck(hId, examineType);
        return res;
    }
}
