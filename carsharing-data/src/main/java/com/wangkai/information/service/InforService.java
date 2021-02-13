package com.wangkai.information.service;

import com.wangkai.information.bean.InfoBean;
import com.wangkai.information.dao.InfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/27.
 */

@Service
public class InforService {
    @Autowired
    private InfoDao inforDao;

    public InfoBean getDetails(String id){
        return inforDao.getDetails(id);
    }

    public boolean add(InfoBean infoBean){
        return inforDao.add(infoBean);
    }

    public Collection<InfoBean> listAllInfo(Map<String,Object> paramsMap){
        return inforDao.listAllInfo(paramsMap);
    }
}
