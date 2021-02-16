package com.wangkai.user.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {

//     `id` int(11) NOT NULL,
//  `avatarUrl` varchar(200) DEFAULT NULL,
//  `city` varchar(100) DEFAULT NULL,
//  `country` varchar(50) DEFAULT NULL,
//  `gender` varchar(10) DEFAULT NULL,
//  `language` varchar(20) DEFAULT NULL,
//  `nickName` varchar(200) DEFAULT NULL,
//  `openId` varchar(200) DEFAULT NULL,
//  `province` varchar(100) DEFAULT NULL,
//  `county` varchar(50) NOT NULL DEFAULT '',
//            `phone` varchar(11) DEFAULT NULL,
//  `vehicle` varchar(200) DEFAULT NULL,
//  `name` varchar(100) NOT NULL

    private Integer id;
    private String avatarUrl;
    private String city;
    private String country;
    private String gender;
    private String language;
    private String nickName;
    private String openId;
    private String province;
    private String county;
    private String phone;
    private String vehicle;
    private String name;
}
