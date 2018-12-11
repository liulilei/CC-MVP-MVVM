package com.androiddesk.base.component.constants

import android.os.Environment

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/4
 */
object Constants {

    val DESK_PATH = Environment.getExternalStorageDirectory().toString() + "/desk"

    val IMG_PATH = "$DESK_PATH/img"

    val GLIDE_CACHE_PATH = "$IMG_PATH/cache"

    const val DEFAULT_PAGE_NO = 0

    const val DEFAULT_PAGE_SIZE = 30

}