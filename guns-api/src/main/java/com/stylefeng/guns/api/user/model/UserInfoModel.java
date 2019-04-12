package com.stylefeng.guns.api.user.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoModel implements Serializable {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private Integer sex;
    private String birthday;
    private Integer lifeState; // 0 单身 1 热恋 2已婚 3 为人父母
    private String biography;  // 简介
    private String address;
    private String headAddress; // 头像url
    private long createTime;
    private long updateTime;

}
