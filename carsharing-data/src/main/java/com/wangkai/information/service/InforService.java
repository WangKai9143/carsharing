package com.wangkai.information.service;

import com.wangkai.information.bean.InforBean;
import com.wangkai.information.dao.InforDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Administrator on 2019/11/27.
 */

@Service
public class InforService {
    @Autowired
    private InforDao inforDao;

    public InforBean getDetails(String id){
        return inforDao.getDetails(id);
    }

    public boolean add(InforBean inforBean){
        return inforDao.add(inforBean);
    }

    public Collection<InforBean> lists(){
        return inforDao.lists();
    }
}
