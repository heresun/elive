package com.sundehui.domain.help;

import com.sundehui.domain.HeatingType;

import java.util.Arrays;

public class FilterParams {

    // 地址模糊查询
    private String address;

    private Integer page;
    private Integer count;
    private Integer type;
    private Integer heatingType;
    // 面积
    private Integer[] square_1;
    private Integer[] square_2;
    private Integer[] square_3;
    private Integer[] square_4;
    private Integer[] square_5;
    private Integer[] square_6;
    private Integer[] square_7;
    //价格
    private Integer[] price_1;
    private Integer[] price_2;
    private Integer[] price_3;
    private Integer[] price_4;
    private Integer[] price_5;
    private Integer[] price_6;
    private Integer[] price_7;
    private Integer[] price_8;
    // 楼层
    private Integer[] level_1;
    private Integer[] level_2;
    private Integer[] level_3;
    //朝向
    private Integer orien_1;
    private Integer orien_2;
    private Integer orien_3;
    private Integer orien_4;

    // 省市区
    private Integer provinceId;
    private Integer cityId;
    private Integer areaId;

    // 标志
    private Integer flag;

    // 停车位
    private Integer parkLot;

    public Integer getParkLot() {
        return parkLot;
    }

    public void setParkLot(Integer parkLot) {
        this.parkLot = parkLot;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(Integer heatingType) {
        this.heatingType = heatingType;
    }

    public Integer[] getSquare_1() {
        return square_1;
    }

    public void setSquare_1(Integer[] square_1) {
        this.square_1 = square_1;
    }

    public Integer[] getSquare_2() {
        return square_2;
    }

    public void setSquare_2(Integer[] square_2) {
        this.square_2 = square_2;
    }

    public Integer[] getSquare_3() {
        return square_3;
    }

    public void setSquare_3(Integer[] square_3) {
        this.square_3 = square_3;
    }

    public Integer[] getSquare_4() {
        return square_4;
    }

    public void setSquare_4(Integer[] square_4) {
        this.square_4 = square_4;
    }

    public Integer[] getSquare_5() {
        return square_5;
    }

    public void setSquare_5(Integer[] square_5) {
        this.square_5 = square_5;
    }

    public Integer[] getSquare_6() {
        return square_6;
    }

    public void setSquare_6(Integer[] square_6) {
        this.square_6 = square_6;
    }

    public Integer[] getSquare_7() {
        return square_7;
    }

    public void setSquare_7(Integer[] square_7) {
        this.square_7 = square_7;
    }

    public Integer[] getPrice_1() {
        return price_1;
    }

    public void setPrice_1(Integer[] price_1) {
        this.price_1 = price_1;
    }

    public Integer[] getPrice_2() {
        return price_2;
    }

    public void setPrice_2(Integer[] price_2) {
        this.price_2 = price_2;
    }

    public Integer[] getPrice_3() {
        return price_3;
    }

    public void setPrice_3(Integer[] price_3) {
        this.price_3 = price_3;
    }

    public Integer[] getPrice_4() {
        return price_4;
    }

    public void setPrice_4(Integer[] price_4) {
        this.price_4 = price_4;
    }

    public Integer[] getPrice_5() {
        return price_5;
    }

    public void setPrice_5(Integer[] price_5) {
        this.price_5 = price_5;
    }

    public Integer[] getPrice_6() {
        return price_6;
    }

    public void setPrice_6(Integer[] price_6) {
        this.price_6 = price_6;
    }

    public Integer[] getPrice_7() {
        return price_7;
    }

    public void setPrice_7(Integer[] price_7) {
        this.price_7 = price_7;
    }

    public Integer[] getPrice_8() {
        return price_8;
    }

    public void setPrice_8(Integer[] price_8) {
        this.price_8 = price_8;
    }

    public Integer[] getLevel_1() {
        return level_1;
    }

    public void setLevel_1(Integer[] level_1) {
        this.level_1 = level_1;
    }

    public Integer[] getLevel_2() {
        return level_2;
    }

    public void setLevel_2(Integer[] level_2) {
        this.level_2 = level_2;
    }

    public Integer[] getLevel_3() {
        return level_3;
    }

    public void setLevel_3(Integer[] level_3) {
        this.level_3 = level_3;
    }

    public Integer getOrien_1() {
        return orien_1;
    }

    public void setOrien_1(Integer orien_1) {
        this.orien_1 = orien_1;
    }

    public Integer getOrien_2() {
        return orien_2;
    }

    public void setOrien_2(Integer orien_2) {
        this.orien_2 = orien_2;
    }

    public Integer getOrien_3() {
        return orien_3;
    }

    public void setOrien_3(Integer orien_3) {
        this.orien_3 = orien_3;
    }

    public Integer getOrien_4() {
        return orien_4;
    }

    public void setOrien_4(Integer orien_4) {
        this.orien_4 = orien_4;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "FilterParams{" +
                "address='" + address + '\'' +
                ", page=" + page +
                ", count=" + count +
                ", type=" + type +
                ", heatingType=" + heatingType +
                ", square_1=" + Arrays.toString(square_1) +
                ", square_2=" + Arrays.toString(square_2) +
                ", square_3=" + Arrays.toString(square_3) +
                ", square_4=" + Arrays.toString(square_4) +
                ", square_5=" + Arrays.toString(square_5) +
                ", square_6=" + Arrays.toString(square_6) +
                ", square_7=" + Arrays.toString(square_7) +
                ", price_1=" + Arrays.toString(price_1) +
                ", price_2=" + Arrays.toString(price_2) +
                ", price_3=" + Arrays.toString(price_3) +
                ", price_4=" + Arrays.toString(price_4) +
                ", price_5=" + Arrays.toString(price_5) +
                ", price_6=" + Arrays.toString(price_6) +
                ", price_7=" + Arrays.toString(price_7) +
                ", price_8=" + Arrays.toString(price_8) +
                ", level_1=" + Arrays.toString(level_1) +
                ", level_2=" + Arrays.toString(level_2) +
                ", level_3=" + Arrays.toString(level_3) +
                ", orien_1=" + orien_1 +
                ", orien_2=" + orien_2 +
                ", orien_3=" + orien_3 +
                ", orien_4=" + orien_4 +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", flag=" + flag +
                ", parkLot=" + parkLot +
                '}';
    }
}
