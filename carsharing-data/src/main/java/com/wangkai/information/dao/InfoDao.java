package com.wangkai.information.dao;


import com.wangkai.information.bean.InfoBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/27.
 */
//@Mapper和在application中@MapperScan选择一种方式使用
public interface InfoDao {
     InfoBean getDetails(int id);

     void addInfo(InfoBean infoBean);

     List<InfoBean> listAllInfo(Map<String,Object> paramsMap);

     List<InfoBean> myInfoList(Map<String, Object> paramsMap);

    void deleteInfo(Map<String,Object> userMap);

    void updateInfo(InfoBean infoBean);
}
