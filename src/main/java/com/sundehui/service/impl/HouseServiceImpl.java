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

        System.out.println("page============================------s笋丁黑i是实打实的发的");
        System.out.println("----->"+param);


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
        }else {
            param.setPage(0);
            param.setCount(10);
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
    public Integer getCountForManage(Integer type, Integer examineType, Date today,
                                     Integer provinceId,Integer cityId, Integer areaId, String address) {

        String addressLike = address==null?null: "%"+address+"%";
        Integer resCount = mapper.getCountForManage(type, examineType, today, provinceId, cityId, areaId, addressLike);

        return resCount;
    }

    @Override
    public List<House> getUnchecked(int page, int count) {

        int from = (page - 1) * count;

        List<House> houses = mapper.getUnchecked(from, count);
        return null;
    }

    @Override
    public int passCheck(Integer hId, Integer examineType) {
        int res = mapper.passCheck(hId, examineType);
        return res;
    }

    @Override
    public List<House> getHousePageForManage(Integer page, Integer count, Integer examineType, Integer provinceId, Integer cityId, Integer areaId, String address) {
        int from = (page-1)*count;

        String addressLike = address==null?null: "%"+address+"%";
        List<House> houses = mapper.getHousePageForManage(from,count,examineType,provinceId,cityId,areaId,addressLike);

        return houses;



    }

    @Override
    public int changeStatus(String houseNumber) {
        int i = mapper.changeStatus(houseNumber);
        return i;
    }

    @Override
    public int changeStatus(Integer id) {
        int i = mapper.changeStatusById(id);
        return i;
    }

    @Override
    public int changeExamineTypeByOwnerId(Integer uId) {
        int resCount = mapper.changeExamineTypeByOwnerId(uId);

        return resCount;
    }

    @Override
    public List<House> getMostCollectedHouses(FilterParams param) {
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

        List<House> housePage = mapper.getMostCollectedHouses(param);
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

    @Override
    public Integer getMostCollectedCount(FilterParams params) {
        Integer count = mapper.getMostCollectedCount(params);
        return count;
    }
}
