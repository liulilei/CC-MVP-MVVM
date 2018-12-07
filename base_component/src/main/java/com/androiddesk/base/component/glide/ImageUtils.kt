package com.androiddesk.base.component.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/4
 */
class ImageUtils {
    companion object {
        /**
         * 加载圆角头像
         *
         * @param context
         * @param rId
         * @param url
         * @param imageView
         */
        fun loadAvatarRound(context: Context, rId: Int, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(GlideRoundTransform())
                    .into(imageView)
        }


        /**
         * 加载确定大小的图片(不缓存
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadExactlyImg(context: Context, rId: Int, url: String?, imageView: ImageView, width: Int, height: Int) {
            GlideApp.with(context)
                    .load(url)
                    .override(width, height)//设置加载图片大小
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .override(width, height)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView)
        }

        /**
         * 加载圆形图片
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadCircleImg(context: Context, rId: Int, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(GlideCircleTransform())
                    .into(imageView)
        }

        /**
         * 加载圆形图片
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadCircleImgWithChat(context: Context, rId: Int, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(GlideCircleTransform())
                    .into(imageView)
        }

        /**
         * 加载圆角图片
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadRoundImg(context: Context, rId: Int, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(GlideRoundTransform())
                    .into(imageView)
        }

        /**
         * 加载普通图片 不缓存本地  缓存在内存中 针对相册里面的图片加载 ---圆角图片
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadRoundImgNotDiskCache(context: Context, rId: Int, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(GlideRoundTransform())
                    .into(imageView)
        }

        /**
         * 加载普通图片 不缓存本地  缓存在内存中 针对相册里面的图片加载 ---图通图片
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadNormalImgNotDiskCache(context: Context, rId: Int, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView)
        }

        /**
         * 加载普通图片
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadNormalImg(context: Context, rId: Int, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
        }

        /**
         * 加载普通图片
         *
         * @param context      上下文
         * @param url          图片的url地址
         * @param simpleTarget bitmap返回回调
         * @param rId          配置默认图片的R文件地址
         */
        fun loadNormalImg(context: Context, rId: Int, url: String?, simpleTarget: SimpleTarget<Bitmap>) {
            GlideApp.with(context)
                    .asBitmap()
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(simpleTarget)
        }


        fun loadCircleImg(context: Context, rId: Int, url: String?, imageview: ImageView, borderWidth: Int, borderColorResId: Int) {
            GlideApp.with(context)
                    .load(url)
                    .centerCrop()
                    .placeholder(rId)
                    .transform(GlideCircleTransform(borderWidth, context.resources.getColor(borderColorResId)))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageview)
        }

        /**
         * 加载普通图片
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         * @param rId       配置默认图片的R文件地址
         */
        fun loadNormalImg(context: Context, rId: Int, url: String?, imageView: ImageView, listener: RequestListener<Drawable>) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .placeholder(rId)
                    .error(rId)
                    .listener(listener)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
        }

        /**
         * 加载普通图片 无默认图
         *
         * @param context   上下文
         * @param url       图片的url地址
         * @param imageView 要设置图片的控件
         */
        fun loadNormalImgNoDefault(context: Context, url: String?, imageView: ImageView) {
            GlideApp.with(context)
                    .load(url)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
        }
    }
}