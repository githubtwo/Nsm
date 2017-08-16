package com.zick.nsm.common.util;

import android.content.Context;
import android.text.TextUtils;


import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class LocalData {

    public static void putPhone(Context context, String json, List<String> list){

        String user_json = JSONUtil.toJSON(list);
        PreferencesUtils.putString(context,json,user_json);
    }

/*    public static void putQueryWater(Context context, String json, List<Querybean> list){

        String user_json = JSONUtil.toJSON(list);
        PreferencesUtils.putString(context,json,user_json);
    }*/


    public static List<String>  getList(Context context,String json){

        String user_json= PreferencesUtils.getString(context,json);
        if(!TextUtils.isEmpty(user_json)){
            return  JSONUtil.fromJson(user_json,List.class);
        }
        return  null;
    }

  /*  public static List<Querybean>  getQueryWaterList(Context context, String json){

        String user_json= PreferencesUtils.getString(context,json);
        if(!TextUtils.isEmpty(user_json)){
            return  JSONUtil.fromJson(user_json,List.class);
        }
        return  null;
    }*/
}
