package com.wangkai.user.dao;

import com.wangkai.user.bean.UserBean;

import java.util.Map;

public interface UserDao {
    UserBean findUserById(String id);

    void saveUser(Map<String, Object> userInfo);

    UserBean findUserByOpenid(String openId);
}
