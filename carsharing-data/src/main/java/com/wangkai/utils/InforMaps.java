package com.wangkai.utils;

import com.wangkai.information.bean.CommentBean;
import com.wangkai.information.bean.InfoBean;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2020/2/16.
 */
public class InforMaps {
    private static Map<String, InfoBean> inforMap = new ConcurrentHashMap<String, InfoBean>();
    private static Map<String,CommentBean> commentMap = new ConcurrentHashMap<String, CommentBean>();

    static {
        InfoBean infoBean = new InfoBean();
        infoBean.setId(10003);
        infoBean.setAddtime(1);
        infoBean.setTime(DateUtils.getCurrentDateTime());
        infoBean.setName("王凯");
        infoBean.setPhone("18629659026");
        infoBean.setGender(1);
        infoBean.setDeparture("西安");
        infoBean.setDestination("韩城");
        infoBean.setSurplus(4);
        infoBean.setVehicle("宝马");
        infoBean.setPrice(50);
        infoBean.setType(1);
        inforMap.put(String.valueOf(1), infoBean);

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

    public static void putInfor(String key, InfoBean val){
        inforMap.put(key,val);
    }

    public static InfoBean getInfor(String key){
        return inforMap.get(key);
    }

    public static Collection<InfoBean> getAllInfos(){
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
