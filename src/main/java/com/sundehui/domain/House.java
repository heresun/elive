package com.sundehui.domain;

import java.math.BigDecimal;
import java.util.Date;

public class House {
    private Integer id;

    private Integer ownerId;

    private String address;

    private Double areaSize;

    private String servLife;

    private Date pubDate;

    private String desc;

    private Integer areaId;

    private Integer provinceId;

    private Integer cityId;

    private Integer type;

    private Integer examineType;

    private Double price;

    private String detailDesc;

    private Integer bedroom;

    private Integer livingroom;

    private Integer kitchen;

    private Integer bathroom;

    private Integer level;

    private Integer electricType;

    private Integer orientation;

    private Integer waterType;

    private Byte naturalGas;

    private Byte parkingLot;

    private Integer heatingType;

    private String houseNumber;

    private Integer renovationType;




    // 辅助字段
    private String url;
    private String electric;
    private String orientedDirection;
    private String water;
    private String heating;
    private String renovation;
    private String owner;
    private Integer cId;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Double getPrice() {
        return price;
    }

    public String getElectric() {
        return electric;
    }

    public void setElectric(String electric) {
        this.electric = electric;
    }

    public String getOrientedDirection() {
        return orientedDirection;
    }

    public void setOrientedDirection(String orientedDirection) {
        this.orientedDirection = orientedDirection;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    private String examine;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(Double areaSize) {
        this.areaSize = areaSize;
    }

    public String getServLife() {
        return servLife;
    }

    public void setServLife(String servLife) {
        this.servLife = servLife == null ? null : servLife.trim();
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public Integer getBedroom() {
        return bedroom;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public Integer getLivingroom() {
        return livingroom;
    }

    public void setLivingroom(Integer livingroom) {
        this.livingroom = livingroom;
    }

    public Integer getKitchen() {
        return kitchen;
    }

    public void setKitchen(Integer kitchen) {
        this.kitchen = kitchen;
    }

    public Integer getBathroom() {
        return bathroom;
    }

    public void setBathroom(Integer bathroom) {
        this.bathroom = bathroom;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getElectricType() {
        return electricType;
    }

    public void setElectricType(Integer electricType) {
        this.electricType = electricType;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Integer getWaterType() {
        return waterType;
    }

    public void setWaterType(Integer waterType) {
        this.waterType = waterType;
    }

    public Byte getNaturalGas() {
        return naturalGas;
    }

    public void setNaturalGas(Byte naturalGas) {
        this.naturalGas = naturalGas;
    }

    public Byte getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(Byte parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Integer getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(Integer heatingType) {
        this.heatingType = heatingType;
    }

    public String getHouseNumber() {
        return houseNumber;
    }


    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber == null ? null : houseNumber.trim();
    }

    public Integer getRenovationType() {
        return renovationType;
    }

    public void setRenovationType(Integer renovationType) {
        this.renovationType = renovationType;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", address='" + address + '\'' +
                ", areaSize=" + areaSize +
                ", servLife='" + servLife + '\'' +
                ", pubDate=" + pubDate +
                ", desc='" + desc + '\'' +
                ", areaId=" + areaId +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", type=" + type +
                ", examineType=" + examineType +
                ", price=" + price +
                ", detailDesc='" + detailDesc + '\'' +
                ", bedroom=" + bedroom +
                ", livingroom=" + livingroom +
                ", kitchen=" + kitchen +
                ", bathroom=" + bathroom +
                ", level=" + level +
                ", electricType=" + electricType +
                ", orientation=" + orientation +
                ", waterType=" + waterType +
                ", naturalGas=" + naturalGas +
                ", parkingLot=" + parkingLot +
                ", heatingType=" + heatingType +
                ", houseNumber='" + houseNumber + '\'' +
                ", renovationType=" + renovationType +
                ", url='" + url + '\'' +
                ", electric='" + electric + '\'' +
                ", orientedDirection='" + orientedDirection + '\'' +
                ", water='" + water + '\'' +
                ", heating='" + heating + '\'' +
                ", renovation='" + renovation + '\'' +
                ", owner='" + owner + '\'' +
                ", cId=" + cId +
                ", examine='" + examine + '\'' +
                '}';
    }
}