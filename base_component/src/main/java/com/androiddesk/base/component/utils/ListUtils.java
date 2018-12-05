package com.androiddesk.base.component.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lll on 2016/8/11.
 */
public class ListUtils {

    /**
     * default join separator
     **/
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    public static <T> int getSize(List<T> list) {
        return list == null ? 0 : list.size();
    }

    public static <T> boolean isEmpty(List<T> list) {
        return (list == null || list.size() == 0);
    }

    public static String join(List<String> list) {
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    public static String join(List<String> list, String separator) {
        return list == null ? "" : TextUtils.join(separator, list);
    }

    public static <T> T getFirst(List<T> list) {
        if (isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public static <T> List<T> sortList(List<T> list) {
        List<T> data = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            data.add(list.get(i));
        }
        return data;
    }

}
