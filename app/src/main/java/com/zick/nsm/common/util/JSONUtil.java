/*
*JSONUtil.java
*Created on 2014-9-29 上午9:54 by Ivan
*Copyright(c)2014 Guangzhou Onion Information Technology Co., Ltd.
*http://www.cniao5.com
*/
package com.zick.nsm.common.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;


public class JSONUtil {


    private static Gson gson = new Gson();
    //Json解析成 java 对象
    public static <T> T fromJson(String json, Class<T> clz){

        return  gson.fromJson(json,clz);
    }
    // 将 json 转化 成 type类型
    public static <T> T fromJson(String json, Type type){

        return  gson.fromJson(json,type);
    }

    //将object转换成json
    public static String toJSON(Object object){
       return gson.toJson(object);
    }

}
