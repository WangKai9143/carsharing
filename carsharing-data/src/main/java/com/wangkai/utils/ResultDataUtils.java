package com.wangkai.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2020/2/15.
 */
public class ResultDataUtils {

    public static String build(Integer code,String  msg){
        ResultData resultData = new ResultData(code,msg);
        return JSON.toJSONString(resultData);
    }

    public static String build(Integer code,String  msg,Object data){
        ResultData resultData = new ResultData(code,msg,data);
        return JSON.toJSONString(resultData);
    }

}
