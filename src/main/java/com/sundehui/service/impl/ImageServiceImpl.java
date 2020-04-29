package com.sundehui.service.impl;

import com.sundehui.domain.Image;
import com.sundehui.mapper.ImageMapper;
import com.sundehui.service.ImageService;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper mapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Image record) {
        int i = mapper.insert(record);
        return i;
    }

    @Override
    public int insertSelective(Image record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public Image selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Image record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Image record) {
        return 0;
    }

    @Override
    public List<String> selectByHouseNumber(String houseNumber) {
        ArrayList<String> uris = new ArrayList<>();
        List<Image> images = mapper.selectByHouseNumber(houseNumber);
        images.forEach(item->{
            uris.add(item.getUri());
        });

        // 返回真实路径列表
        return uris;
    }

    @Override
    public Integer deleteOne(String houseNumber, String imgName) {
        Integer integer = mapper.deleteOne(houseNumber, imgName);

        return integer;
    }

    @Override
    public Integer deleteByHouseNumber(String houseNumber) {
        Integer i = mapper.deleteByHouseNumber(houseNumber);
        return i;
    }

    @Override
    public String getImgRealPath(String houseNumber, String imageName) {
        String realPath = mapper.getImgRealPath(houseNumber, imageName);
        return realPath;
    }
}
