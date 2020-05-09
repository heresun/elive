package com.sundehui.service.impl;

import com.sundehui.domain.HouseProve;
import com.sundehui.mapper.HouseProveMapper;
import com.sundehui.service.HouseProveService;
import com.sundehui.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class HouseProveServiceImpl implements HouseProveService {



    @Autowired
    private HouseProveMapper mapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int insert(HouseProve record) {
        int insert = mapper.insert(record);
        return insert;
    }

    @Override
    public int insertSelective(HouseProve record) {
        int i = mapper.insertSelective(record);
        return i;
    }

    @Override
    public HouseProve selectByPrimaryKey(Integer id) {
        HouseProve houseProve = mapper.selectByPrimaryKey(id);
        return houseProve;
    }

    @Override
    public int updateByPrimaryKeySelective(HouseProve record) {
        int i = mapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public int updateByPrimaryKey(HouseProve record) {
        int i = mapper.updateByPrimaryKey(record);
        return i;
    }

    @Override
    public List<String> getAll(String houseNumber) {

        List<HouseProve> houseProves = mapper.getAll(houseNumber);
        List<String> proveUrls = new ArrayList<>();

        houseProves.forEach(item -> {
            String s = ImgUtil.realPathToUrl(request, item.getUri());
            proveUrls.add(s);
//            proveUrls.add(item.getUri());
        });

        return proveUrls;
    }

    @Override
    public int deleteOne(String houseNumber, String imageName) {
        String imgName = "%"+imageName+"%";
        int i = mapper.deleteOne(houseNumber, imgName);
        return i;
    }
}
