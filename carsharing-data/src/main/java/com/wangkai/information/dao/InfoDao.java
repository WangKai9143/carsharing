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
    public InfoBean getDetails(String id);

    public boolean add(InfoBean infoBean);

    public List<InfoBean> listAllInfo(Map<String,Object> paramsMap);
}
