package com.sundehui.service;

import com.sundehui.domain.*;

import java.util.List;

public interface TypeService {


    List<ElectricType> getAllElectricType();

    List<HeatingType> getAllHeatingType();


    List<Orientation> getAllOrientationType();
    Orientation getOneOrientation(Integer id);

    List<Renovation> getAllRenovationType();
    Renovation getOneRenovationType(Integer id);


    List<WaterType> getAllWaterType();






}
