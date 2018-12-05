package com.androiddesk.base.component.utils

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Looper
import android.telephony.TelephonyManager
import android.text.TextUtils
import java.io.File

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */
class AppUtils {

    companion object {
        /**
         * @return 获取应用版本号。
         */
        fun getAppVersionCode(context: Context): Int {
            var versionCode = 1

            try {
                versionCode = context.packageManager.getPackageInfo(context.packageName, 1).versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                return versionCode
            }

            return versionCode
        }

        /**
         * Get version name for application.
         *
         * @param context
         * @return 获取应用版本名
         */
        fun getAppVersionName(context: Context): String {
            var versionName = "1.0.0"

            try {
                versionName = context.packageManager.getPackageInfo(context.packageName, 1).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return versionName
        }

        /**
         * @param context
         * @return 获取手机的imei值，手机的唯一标示
         */
        fun getDeviceImei(context: Context): String {
            return (context.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager).deviceId
        }

        /**
         * @return 获取手机当前版本号
         */
        fun getDeviceVersion(): String {
            return android.os.Build.VERSION.RELEASE
        }

        /**
         * 获取手机机型
         *
         * @return
         */
        fun getDeviceName(): String {
            return android.os.Build.MODEL
        }

        /**
         * 获取application中指定的meta-data
         *
         * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
         */
        fun getAppMetaData(context: Context, key: String?): String {
            var resultData = ""
            try {
                val packageManager = context.packageManager
                if (packageManager != null) {
                    val applicationInfo = packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
                    if (applicationInfo != null) {
                        if (applicationInfo.metaData != null) {
                            if (applicationInfo.metaData.getString(key) != null) {
                                resultData = applicationInfo.metaData.getString(key) + ""
                            }
                        }
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                return ""
            }

            return resultData
        }

        /**
         * 打开一个apk的安装包
         */
        fun installApk(context: Context, dir: String, fileName: String) {
            if (TextUtils.isEmpty(dir) || TextUtils.isEmpty(fileName)) {
                return
            }
            val fileDir = File(dir)
            val file = File(dir, fileName)
            if (!fileDir.exists()) {
                return
            }
            if (file.exists()) {
                val intent = Intent()
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.action = Intent.ACTION_VIEW
                val uri = Uri.fromFile(file)
                intent.setDataAndType(uri, "application/vnd.android.package-archive")
                context.startActivity(intent)
            }
        }

        /**
         * 打开一个apk的安装包
         */
        fun installApk(context: Context, fileSavePath: String) {
            if (TextUtils.isEmpty(fileSavePath)) {
                return
            }
            val file = File(fileSavePath)
            if (!file.exists()) {
                return
            }
            if (file.exists()) {
                val intent = Intent()
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.action = Intent.ACTION_VIEW
                val uri = Uri.fromFile(file)
                intent.setDataAndType(uri, "application/vnd.android.package-archive")
                context.startActivity(intent)
            }
        }

        /**
         * 获取应用所有的包名
         *
         * @param context 上下文
         * @return
         */
        fun getAllPackName(context: Context): List<String>? {
            val packManager = context.packageManager
            val packInfos = packManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES)
            if (packInfos == null || packInfos.size == 0) {
                return null
            }
            return packInfos.map { it.packageName }
        }

        /**
         * whether application is in background
         *
         *  * need use permission android.permission.GET_TASKS in Manifest.xml
         *
         *
         * @param context 上下文
         * @return if application is in background return true, otherwise return
         * false
         */
        fun isApplicationInBackground(context: Context): Boolean {
            val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val taskList = am.getRunningTasks(1)
            if (taskList != null && !taskList.isEmpty()) {
                val topActivity = taskList[0].topActivity
                if (topActivity != null && topActivity.packageName != context.packageName) {
                    return true
                }
            }
            return false
        }

        fun isActivityLunch(context: Context, clz: String): Boolean {
            var isActive = false
            if (!TextUtils.isEmpty(clz)) {
                val am = context.getSystemService(Activity.ACTIVITY_SERVICE) as ActivityManager
                val taskInfoList = am.getRunningTasks(10)
                for (taskInfo in taskInfoList) {
                    if (taskInfo.baseActivity.className == clz) { // 说明它已经启动了
                        isActive = true
                        break
                    }
                }
            }
            return isActive
        }

        /**
         * 检测当前是否为主线程
         *
         * @return true 是
         */
        fun isInMainThread(): Boolean {
            return Looper.myLooper() == Looper.getMainLooper()
        }

        /**
         * 检测当前是否有安装该应用
         *
         * @return true 是
         */
        fun isAppInstall1(context: Context, packageName: String): Boolean {
            return try {
                context.packageManager.getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                false
            }

        }

    }

}