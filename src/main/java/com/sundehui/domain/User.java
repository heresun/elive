package com.sundehui.domain;

import java.util.List;

public class User {
    private Integer id;

    private String account;

    private String password;

    private String username;

    private String phone;

    private String photo;

    private Integer roleId;

    private String roleName;

    private Integer examineType;

    private List<String> proveUrls;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getProveUrls() {
        return proveUrls;
    }

    public void setProveUrls(List<String> proveUrls) {
        this.proveUrls = proveUrls;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public User(String account, String password, String username, String phone, String photo) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.photo = photo;
    }

    public User() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", examineType=" + examineType +
                '}';
    }
}