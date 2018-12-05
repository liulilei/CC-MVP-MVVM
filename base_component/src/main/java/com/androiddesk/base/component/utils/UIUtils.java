package com.androiddesk.base.component.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ll on 2015/11/12.
 */
public class UIUtils {

    /**
     * 判断这个所有Activity中是否启动了目标Activity
     *
     * @param context     上下文
     * @param packageName
     * @return
     */
    public static boolean isActivityLunch(Context context, String packageName) {
        boolean isActive = false;
        if (!TextUtils.isEmpty(packageName)) {
            ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.getClassName().contains(packageName)) { // 说明它已经启动了
                    isActive = true;
                    break;
                }
            }
        }
        return isActive;
    }

    /**
     * 判断目标Activity是否在应用顶部显示
     *
     * @param context      上下文
     * @param activityName 目标activity的数组
     * @return true 在 false 不在
     */
    public static boolean isTopActivity(Context context, String[] activityName) {
        if (isKeyguardRestrictedInputMode(context)) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // 应用程序位于堆栈的顶层
            if (activityName != null && activityName.length > 0) {
                for (String name : activityName) {
                    if (name.equals(tasksInfo.get(0).topActivity.getClassName())) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     *
     * @param context
     * @return
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取手机是否在锁屏状态下
     *
     * @param context 上下文
     * @return
     */
    public static boolean isKeyguardRestrictedInputMode(Context context) {
        return ((KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE)).inKeyguardRestrictedInputMode();
    }

    /**
     * 当listview的item显示和隐藏的时候如果高度改变较大需要重新计算高度，不然会报错。
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   //listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);  //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight();  //统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //listView.getDividerHeight()获取子项间分隔符占用的高度
        //params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public static void setGridViewHeightBasedOnChildren(GridView listView, int num) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = num;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listView.getChildAt(i);
//            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
//                    View.MeasureSpec.EXACTLY);
//            listItem.measure(desiredWidth, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
//            LogUtils.d(LogUtils.AuthorType_ZhiQi,"i : "+i+" | height : "+ listItem.getMeasuredHeight());
        }
        // 获取listview的布局参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) listView.getLayoutParams();
        // 设置高度
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            if (num == 3) {
                params.height = totalHeight
                        + ((listAdapter.getCount() / 3) * 15);
            } else {
                params.height = totalHeight;
            }
            return;
        } else {
            params.height = totalHeight
                    + (listView.getVerticalSpacing() * (listAdapter.getCount() / num));
        }
//        LogUtils.d(LogUtils.AuthorType_ZhiQi,"params.height : "+params.height);
        // 设置参数
        listView.setLayoutParams(params);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public final static void hideInputKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public final static void hideInputKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.getWindow().getDecorView();
        if (view == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打开软键盘
     *
     * @param view
     */
    public final static void showInputKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void windowAlpha(Context context, float alpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = alpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    public static void setTextViewType(Context context, TextView tv, int drawableId, int colorId) {
        Drawable tvDrawable = context.getResources().getDrawable(drawableId);
        tvDrawable.setBounds(0, 0, tvDrawable.getMinimumWidth(), tvDrawable.getMinimumHeight());
        tv.setCompoundDrawables(tvDrawable, null, null, null);
        tv.setTextColor(context.getResources().getColor(colorId));
    }

}

