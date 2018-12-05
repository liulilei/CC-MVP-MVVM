package com.androiddesk.base.component.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;


/**
 * @Description:
 * @author: XV
 * @date: 2018/11/8
 */

public class RichTextUtils {

    public static Spannable getStringSize(String text,int startTextSize,int endTextSize,int endPosition){
        Spannable sp = new SpannableString(text);
        sp.setSpan(new AbsoluteSizeSpan(startTextSize, true), 0, endPosition, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(endTextSize, true), endPosition+1, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sp;
    }
}
