package com.sundehui.service.impl;

import com.sundehui.domain.*;
import com.sundehui.mapper.*;
import com.sundehui.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    ElectricTypeMapper electricTypeMapper;

    @Autowired
    HeatingTypeMapper heatingTypeMapper;

    @Autowired
    OrientationMapper orientationMapper;

    @Autowired
    RenovationMapper renovationMapper;

    @Autowired
    WaterTypeMapper waterTypeMapper;

    @Override
    public List<ElectricType> getAllElectricType() {
        List<ElectricType> all = electricTypeMapper.getAll();
        return all;
    }

    @Override
    public List<HeatingType> getAllHeatingType() {
        List<HeatingType> all = heatingTypeMapper.getAll();
        return all;
    }

    @Override
    public List<Orientation> getAllOrientationType() {
        List<Orientation> all = orientationMapper.getAll();
        return all;
    }

    @Override
    public Orientation getOneOrientation(Integer id) {
        Orientation orientation = orientationMapper.selectByPrimaryKey(id);
        return orientation;
    }

    @Override
    public List<Renovation> getAllRenovationType() {
        List<Renovation> all = renovationMapper.getAll();
        return all;
    }

    @Override
    public Renovation getOneRenovationType(Integer id) {
        Renovation renovation = renovationMapper.selectByPrimaryKey(id);
        return renovation;
    }

    @Override
    public List<WaterType> getAllWaterType() {
        List<WaterType> all = waterTypeMapper.getAll();
        return all;
    }
}
