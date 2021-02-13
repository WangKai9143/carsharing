package com.wangkai.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2020/2/14.
 */
public class DateUtils {
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateTime(){
        return sf.format(new Date());
    }

    public static Date getCurrentDate(){
        try {
            return sf.parse(getCurrentDateTime());
        } catch (ParseException e) {
            return null;
        }

    }

}
