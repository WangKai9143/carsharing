package com.wangkai.user.service;

import com.wangkai.common.GlobalCache;
import com.wangkai.user.bean.UserBean;
import com.wangkai.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public int getUId(String sk) {
        Map<String,Object> userMap = (Map<String, Object>) GlobalCache.get(sk);
        int uId = Integer.valueOf(userMap.get("id").toString());
        return uId;
    }

    public UserBean getUserInfo(String id) {
        return userDao.findUserById(id);
    }

    public void saveUser(Map<String,Object> userInfo) {
        userDao.saveUser(userInfo);
    }

    public UserBean getUserInfoByOpenid(String openId) {
        return userDao.findUserByOpenid(openId);
    }
}
