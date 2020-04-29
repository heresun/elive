package com.sundehui.mapper;

import com.sundehui.domain.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Image record);

    int insertSelective(Image record);

    Image selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> selectByHouseNumber(@Param("houseNumber") String hosueNumber);

    // 通过房源编号和图片的名称，删除一个图片
    Integer deleteOne(@Param("houseNumber") String houseNumber, @Param("imgName") String imgName);

    // 根据房屋编号删除其所有的图片
    Integer deleteByHouseNumber(@Param("houseNumber") String houseNumber);

    // 根据房屋编号和图片名称获取图片的真实路径
    String getImgRealPath(@Param("houseNumber") String houseNumber, @Param("imgName") String imgName);
}