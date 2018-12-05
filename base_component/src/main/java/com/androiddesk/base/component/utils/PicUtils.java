package com.androiddesk.base.component.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

public class PicUtils {
    /**
     * 获取图片路径 (相册取图返回 onActivityResult data)
     *
     * @param context
     * @param data    onActivityResult 的返回数据
     * @return
     */
    public static String getPicPathFromResult(Context context, Intent data) {
        String picPath;
        Uri selectedImage = data.getData();
        if (selectedImage.toString().contains("file:")) {
            picPath = selectedImage.toString().replace("file://", "");
        } else {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picPath = cursor.getString(columnIndex);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                cursor.close();
            }
        }
        return picPath;
    }
}
