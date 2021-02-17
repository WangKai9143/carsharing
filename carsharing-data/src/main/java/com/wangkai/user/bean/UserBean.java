package com.wangkai.user.bean;

import com.wangkai.common.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBean extends BaseBean {
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
