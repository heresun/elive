package com.sundehui.domain;

public class Recommend {
    private Integer id;

    private Integer areaId;

    private Integer userId;

    private Integer times;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }


    @Override
    public String toString() {
        return "Recommend{" +
                "id=" + id +
                ", areaId=" + areaId +
                ", userId=" + userId +
                ", times=" + times +
                '}';
    }
}