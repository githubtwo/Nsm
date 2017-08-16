package com.zick.nsm.common.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    public static String PHONE_DATA = "Phone_data";
    public static String WATER_DATA = "Water_data";


    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(key, Context.MODE_PRIVATE);//私有数据
        SharedPreferences.Editor editor = settings.edit();//获取编辑器
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

}
