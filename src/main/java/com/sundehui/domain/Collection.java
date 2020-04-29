package com.sundehui.domain;

public class Collection {
    private Integer id;

    private Integer houseId;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", houseId=" + houseId +
                ", userId=" + userId +
                '}';
    }
}