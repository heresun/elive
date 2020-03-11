package com.sundehui.domain;

import org.springframework.stereotype.Component;

@Component
public class House {
    private Integer id;

    private Integer owner;

    private String address;

    private Double areaSize;

    private String servLife;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}