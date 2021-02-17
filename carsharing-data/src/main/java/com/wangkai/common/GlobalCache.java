package com.wangkai.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GlobalCache  {
    private static Map<String,Object> userCaches = new HashMap<>();

    public static void put(String key,Object value){
        userCaches.put(key,value);
    }

    public static Object get(String key){
        return userCaches.get(key);
    }
}
