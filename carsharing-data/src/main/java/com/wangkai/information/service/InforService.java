package com.wangkai.information.service;

import com.github.pagehelper.PageHelper;
import com.wangkai.information.bean.InfoBean;
import com.wangkai.information.dao.InfoDao;
import com.wangkai.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/27.
 */

@Service
public class InforService {
    @Autowired
    private InfoDao infoDao;

    @Autowired
    private UserService userService;

    private static int pageSize = 5;

    public InfoBean getDetails(int id) {
        return infoDao.getDetails(id);
    }

    public void addInfo(InfoBean infoBean) {
        int uId = userService.getUId(infoBean.getSk());
        infoBean.setUid(uId);
        infoBean.setTime(String.valueOf(System.currentTimeMillis()/1000));
        infoBean.setStatus(1);
        infoDao.addInfo(infoBean);
    }


    public List<InfoBean> listAllInfo(Map<String, Object> paramsMap) {
        int currPage = 1;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            currPage = Integer.parseInt(paramsMap.get("page").toString());
        }
        PageHelper.startPage(currPage, pageSize);
        List<InfoBean> infoBeanList = infoDao.listAllInfo(paramsMap);
        return infoBeanList;
    }

    public List<InfoBean> myInfoList(Map<String, Object> paramsMap) {
        int uId = userService.getUId(paramsMap.get("sk").toString());
        paramsMap.put("uid",uId);
        int currPage = 1;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            currPage = Integer.parseInt(paramsMap.get("page").toString());
        }
        PageHelper.startPage(currPage, pageSize);
        List<InfoBean> infoBeanList = infoDao.myInfoList(paramsMap);
        return infoBeanList;
    }

    public void deleteInfo(int id, String sk) {
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("sk",sk);
        userMap.put("id",id);
        infoDao.deleteInfo(userMap);
    }

    public void updateInfo(InfoBean infoBean) {
        infoBean.setTime(String.valueOf(System.currentTimeMillis()/1000));
        infoDao.updateInfo(infoBean);
    }
}
