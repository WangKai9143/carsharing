package com.wangkai.utils;

import com.wangkai.information.bean.CommentBean;
import com.wangkai.information.bean.InforBean;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2020/2/16.
 */
public class InforMaps {
    private static Map<String,InforBean> inforMap = new ConcurrentHashMap<String, InforBean>();
    private static Map<String,CommentBean> commentMap = new ConcurrentHashMap<String, CommentBean>();

    static {
        InforBean inforBean = new InforBean();
        inforBean.setId(10003);
        inforBean.setAddtime(1);
        inforBean.setTime(DateUtils.getCurrentDateTime());
        inforBean.setName("王凯");
        inforBean.setPhone("18629659026");
        inforBean.setGender(1);
        inforBean.setDeparture("西安");
        inforBean.setDestination("韩城");
        inforBean.setSurplus(4);
        inforBean.setVehicle("宝马");
        inforBean.setPrice(50);
        inforBean.setType(1);
        inforMap.put(String.valueOf(1),inforBean);

        //===========================
        CommentBean commentBean = new CommentBean();
        commentBean.setId(1);
        commentBean.setIid(10003);
        commentBean.setUid(1);
        commentBean.setTime(DateUtils.getCurrentDate());
        commentBean.setType("info");
        commentBean.setContent("666666");
        commentBean.setImg("");
        commentBean.setReply("repley 。。。");
        commentBean.setZan(0);
        commentMap.put("info$10003",commentBean);
    }

    public static void putInfor(String key,InforBean val){
        inforMap.put(key,val);
    }

    public static InforBean getInfor(String key){
        return inforMap.get(key);
    }

    public static Collection<InforBean> getAllInfos(){
        return inforMap.values();
    }

    public static void putComment(String key,CommentBean val){
        commentMap.put(key,val);
    }

    public static CommentBean getComment(String key){
        return commentMap.get(key);
    }
    public static Collection<CommentBean> getAllComments(){
        return commentMap.values();
    }
}
