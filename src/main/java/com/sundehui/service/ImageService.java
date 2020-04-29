package com.sundehui.service;

import com.sundehui.domain.Image;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ImageService {

    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    // 通过房源编号获取房屋的图片url
    List<String> selectByHouseNumber(String houseNumber);

    // 通过房源编号和图片的名称，删除一个图片
    Integer deleteOne(String houseNumber, String imgName);

    // 根据房屋编号删除其所有的图片
    Integer deleteByHouseNumber(String houseNumber);

    // 根据房屋编号和图片名称获取图片的真实路径
    String getImgRealPath(String houseNumber, String imageName);
}
