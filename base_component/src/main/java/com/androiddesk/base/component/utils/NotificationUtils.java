package com.androiddesk.base.component.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by lll on 2016/5/6.
 */
public class NotificationUtils {

    public static void cancelAllNotification(Context context) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelTagNotification(Context context, int tag) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Notification notify(Context context, String contentTile, String contentText, String ticker, int smallIcon, int largeIcon,
                                      long time, boolean autoCancel, int vibrate, int soundUri, PendingIntent pendingIntent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setContentTitle(contentTile); // 设置内容题目
        builder.setContentText(contentText); // 设置内容文本信息
        builder.setTicker(ticker);

        builder.setSmallIcon(smallIcon); // 设置小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon));
        builder.setWhen(time); // 设置时间
        builder.setAutoCancel(autoCancel); // 默认点击对应的notification对象后，该对象消失
        builder.setDefaults(vibrate);
        builder.setDefaults(soundUri);

        builder.setContentIntent(pendingIntent);
        // 得到一个notification对象(根据builder预设置信息)
        return builder.build();
    }

}
