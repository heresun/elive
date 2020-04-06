package com.sundehui.domain;

import java.sql.Timestamp;
import java.util.Date;

public class House {
    private Integer id;

    private Integer cId;

    private Integer ownerId;

    private String owner;

    private String address;

    private Double areaSize;

    private String servLife;

    private Timestamp pubDate;

    private String layout;

    private String desc;

    private String detailDesc;

    private Integer areaId;

    private Integer tagId;

    private String tag;

    private Integer examineType;

    private double price;

    public Integer getcId() {
        return cId;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", cId=" + cId +
                ", ownerId=" + ownerId +
                ", owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                ", areaSize=" + areaSize +
                ", servLife='" + servLife + '\'' +
                ", pubDate=" + pubDate +
                ", layout='" + layout + '\'' +
                ", desc='" + desc + '\'' +
                ", detailDesc='" + detailDesc + '\'' +
                ", areaId=" + areaId +
                ", tagId=" + tagId +
                ", tag='" + tag + '\'' +
                ", examineType=" + examineType +
                ", price=" + price +
                '}';
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCId() {
        return cId;
    }

    public void setCId(Integer cId) {
        this.cId = cId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
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

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

}