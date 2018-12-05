package com.androiddesk.base.component.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;


public class ParseDataUtils {

    public static <T> T parseGSON(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    //根据 Type 解析
    public static <T> T parseGSON(String jsonString, Type typeOfT) {
        T t = null;
        try {
            t = new Gson().fromJson(jsonString, typeOfT);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    public static String toJson(Object value) {
        Gson gson = new Gson();
        String str = gson.toJson(value);
        return str;
    }

}
