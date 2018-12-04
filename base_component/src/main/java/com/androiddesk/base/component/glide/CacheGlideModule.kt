package com.androiddesk.base.component.glide

import android.content.Context
import com.androiddesk.base.component.constants.Constants
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper
import com.bumptech.glide.module.AppGlideModule
import java.io.File

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/4
 */

@GlideModule
class CacheGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache {
            val cacheDir = File(Constants.GLIDE_CACHE_PATH)
            if (!cacheDir.exists()) {
                cacheDir.mkdirs()
            }
            DiskLruCacheWrapper.create(cacheDir, (100 * 1024 * 1024).toLong())
        }
    }

}