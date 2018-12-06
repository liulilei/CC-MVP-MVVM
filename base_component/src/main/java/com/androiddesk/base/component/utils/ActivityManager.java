package com.androiddesk.base.component.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Activity管理类
 */
public class ActivityManager {
    private static final String TAG = "ActivityManager";

    private static List<SupportActivity> activityStack;

    private static ActivityManager instance;

    public static ActivityManager getAppManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        if (activityStack == null) {
            activityStack = new ArrayList<>();
        }
        return instance;
    }

    /**
     * 压栈
     */
    public void addActivity(SupportActivity activity) {
        activityStack.add(activity);
    }


    /**
     * 移出
     */
    public void removeActivity(SupportActivity activity) {
        if (null != activity) {
            activityStack.remove(activity);
        }
    }

    public List<SupportActivity> getActivityStack() {
        return activityStack;
    }


    /**
     * 结束当前Activity
     */
    public void finishActivity(Activity activity) {
        if (null != activity) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 关闭倒数二个
     */
    public void finishLastTwoActivity() {
        if (activityStack.size() > 2) {
            activityStack.get(activityStack.size() - 2).finish();
            activityStack.get(activityStack.size() - 1).finish();
        }
    }

    /**
     * @return void    返回类型
     * @Description: TODO(保留首页的Activity)
     */
    public void keepFirstActivity() {
        for (int i = 1; i < activityStack.size(); i++) {
            activityStack.get(i).finish();
        }
    }

    /**
     * 结束所有Activity，但保留最后一个
     */
    public void finishActivitiesAndKeepLastOne() {
        for (int i = 0, size = activityStack.size() - 1; i < size; i++) {
            activityStack.get(0).finish();
            activityStack.remove(0);
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAllActivity() {
        if (activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
            activityStack = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivityClass(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    activityStack.remove(activity);
                    finishActivity(activity);
                    break;
                }
            }
        }

    }

}