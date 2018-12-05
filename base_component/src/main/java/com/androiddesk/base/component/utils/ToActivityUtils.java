package com.androiddesk.base.component.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

import java.io.File;
import java.util.List;

/**
 * Created by lll on 2016/1/5.
 */
public class ToActivityUtils {
    /**
     * @param packageContext
     * @param action         含操作的Intent
     * @Description: 隐式启动, 跳转
     */
    public static void startActivityIntentSafe(Context packageContext, Intent action) {
        // Verify it resolves
        PackageManager packageManager = packageContext.getPackageManager();
        List activities = packageManager.queryIntentActivities(action, PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            packageContext.startActivity(action);
        }

    }

    /**
     * @param packageContext
     * @param cls
     * @param keys
     * @param values         手动改变int[] values类型,可以传递其它数据类型,就不重载了
     * @Description: 跳转, 带参数的方法;需要其它的数据类型,再继续重载吧,暂时只写这么多吧,意义不大
     */
    public static void toNextActivity(Context packageContext, Class<?> cls, String[] keys, int[] values) {
        Intent i = new Intent(packageContext, cls);
        for (int j = 0; j < values.length; j++) {
            i.putExtra(keys[j], values[j]);
        }
        packageContext.startActivity(i);

    }

    /**
     * @param packageContext from,一般传XXXActivity.this
     * @param cls            to,一般传XXXActivity.class
     * @Description: 跳转
     */
    public static void toNextActivity(Context packageContext, Class<?> cls) {
        Intent i = new Intent(packageContext, cls);
        packageContext.startActivity(i);
    }

    /**
     * @param packageContext
     * @param cls
     * @param key            需要传进去的String参数{{key1,values},{key2,value2}...}
     * @Description: 跳转, 带参数的方法;需要其它的数据类型,再继续重载吧
     */
    public static void toNextActivity(Context packageContext, Class<?> cls, String key, String value) {
        Intent i = new Intent(packageContext, cls);
        i.putExtra(key, value);
        packageContext.startActivity(i);

    }


    /**
     * @param packageContext
     * @param cls
     * @param key            需要传进去的String参数{{key1,values},{key2,value2}...}
     * @Description: 跳转, 带参数的方法;需要其它的数据类型,再继续重载吧
     */
    public static void toNextActivity(Context packageContext, Class<?> cls, String key, boolean value) {
        Intent i = new Intent(packageContext, cls);
        i.putExtra(key, value);
        packageContext.startActivity(i);
    }

    /**
     * @param packageContext
     * @param cls
     * @param key            需要传进去的String参数{{key1,values},{key2,value2}...}
     * @Description: 跳转, 带参数的方法;需要其它的数据类型,再继续重载吧
     */
    public static void toNextActivity(Context packageContext, Class<?> cls, String key, int value) {
        Intent i = new Intent(packageContext, cls);
        i.putExtra(key, value);
        packageContext.startActivity(i);
    }

    /**
     * @param packageContext
     * @param cls
     * @param key            需要传进去的String参数{{key1,values},{key2,value2}...}
     * @Description: 跳转, 带参数的方法;需要其它的数据类型,再继续重载吧
     */
    public static void toNextActivity(Context packageContext, Class<?> cls, String key, double value) {
        Intent i = new Intent(packageContext, cls);
        i.putExtra(key, value);
        packageContext.startActivity(i);
    }

    public static void toNextActivityAndFinish(Context packageContext, Class<?> cls) {
        Intent i = new Intent(packageContext, cls);
        packageContext.startActivity(i);

        ((Activity) packageContext).finish();
    }

    public static void finish(Activity activity) {
        activity.finish();
    }

    public static void openCamera(Context context, String fileName, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fileName)));
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public static void openPhoto(Context context, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public static void toNextActivity(Context packageContext, Class<?> cls, String key, String value, String key1, String value1) {
        Intent i = new Intent(packageContext, cls);
        i.putExtra(key, value);
        i.putExtra(key1, value1);
        packageContext.startActivity(i);

    }

    public static void openApplicationDetail(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

}
