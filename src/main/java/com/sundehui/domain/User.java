package com.sundehui.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String account;
    private String password;
    private String nickName;
    private String address;
    private String phone;
    private String photo;
    private Integer roleId;
}
