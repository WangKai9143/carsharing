package com.wangkai.information.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    public InfoBean getDetails(String id) {
        return inforDao.getDetails(id);
    }

    public boolean add(InfoBean infoBean) {
        return inforDao.add(infoBean);
    }

    public List<InfoBean> listAllInfo(Map<String, Object> paramsMap) {
        int currPage = 1;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            currPage = Integer.parseInt(paramsMap.get("page").toString());
        }
        PageHelper.startPage(currPage, pageSize);
        List<InfoBean> infoBeanList = inforDao.listAllInfo(paramsMap);
//        Page<InfoBean> count = (Page<InfoBean>)infoBeanList;
        return infoBeanList;
    }
}
