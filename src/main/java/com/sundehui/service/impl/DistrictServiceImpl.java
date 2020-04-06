package com.sundehui.service.impl;

import com.sundehui.domain.District;
import com.sundehui.mapper.DistrictMapper;
import com.sundehui.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper mapper;

    // 根据房屋的areaId查找房屋所在地区
    @Override
    public District selectByAreaId(Integer id) {
        District district = mapper.selectByAreaId(id);
        return district;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(District record) {
        return 0;
    }

    @Override
    public int insertSelective(District record) {
        return 0;
    }

    @Override
    public District selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(District record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(District record) {
        return 0;
    }
}
