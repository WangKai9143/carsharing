package com.wangkai.information.dao;


import com.wangkai.information.bean.InforBean;
import com.wangkai.utils.InforMaps;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Administrator on 2019/11/27.
 */

@Component
public class InforDao {
    public InforBean getDetails(String id){
        try {
            return  InforMaps.getInfor(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean add(InforBean inforBean){
        try {
            InforMaps.putInfor(String.valueOf(inforBean.getId()),inforBean);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Collection<InforBean> lists(){
        try {
            Collection<InforBean> appoints = InforMaps.getAllInfos();
            return appoints;
        } catch (Exception e) {
            return null;
        }
    }
}
