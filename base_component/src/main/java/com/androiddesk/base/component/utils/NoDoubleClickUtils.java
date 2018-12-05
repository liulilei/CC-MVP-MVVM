package com.androiddesk.base.component.utils;

/**
 * Created by lll on 2016/3/14.
 */
public class NoDoubleClickUtils {

    private static long lastClickTime = 0;

    private final static int SPACE_TIME = 2000;

    private static long sendMessageLastClickTime = 0;

    private final static int MESSAGE_SPACE_TIME = 500;

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }

    public synchronized static boolean isDoubleClickPrice() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - sendMessageLastClickTime > MESSAGE_SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        sendMessageLastClickTime = currentTime;
        return isClick2;
    }

}
