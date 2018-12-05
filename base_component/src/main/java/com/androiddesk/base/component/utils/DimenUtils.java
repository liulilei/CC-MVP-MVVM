package com.androiddesk.base.component.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DimenUtils {

    private static DisplayMetrics mDisplayMetrics;

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = context.getResources().getDisplayMetrics();
        }
        return mDisplayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = context.getResources().getDisplayMetrics();
        }
        return mDisplayMetrics.heightPixels;
    }


    /**
     * 将dip或dp值转换为px值
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 将px值转换为dip或dp值
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 将px值转换为sp值
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static float getDimension(Context context, int resId) {
        return context.getResources().getDimension(resId);
    }

}
