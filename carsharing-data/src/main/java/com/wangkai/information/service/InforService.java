package com.wangkai.information.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangkai.common.GlobalCache;
import com.wangkai.information.bean.InfoAndUserVo;
import com.wangkai.information.bean.InfoBean;
import com.wangkai.information.dao.InfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/27.
 */

@Service
public class InforService {
    @Autowired
    private InfoDao inforDao;

    private static int pageSize = 5;

    public InfoBean getDetails(int id) {
        return inforDao.getDetails(id);
    }

    public void addInfo(InfoBean infoBean) {
        Map<String,Object> userMap = (Map<String, Object>) GlobalCache.get(infoBean.getSk());
        infoBean.setUid(Integer.valueOf(userMap.get("id").toString()));
        infoBean.setTime(String.valueOf(System.currentTimeMillis()/1000));
        infoBean.setStatus(1);
        inforDao.addInfo(infoBean);
    }

    public List<InfoBean> listAllInfo(Map<String, Object> paramsMap) {
        int currPage = 1;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            currPage = Integer.parseInt(paramsMap.get("page").toString());
        }
        PageHelper.startPage(currPage, pageSize);
        List<InfoBean> infoBeanList = inforDao.listAllInfo(paramsMap);
        return infoBeanList;
    }
}
