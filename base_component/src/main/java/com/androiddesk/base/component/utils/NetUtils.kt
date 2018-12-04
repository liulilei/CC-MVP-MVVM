package com.androiddesk.base.component.utils

import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import com.androiddesk.base.component.net.ResultCode

class NetUtils {

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */
    fun getNetworkType(mContext: Context): Int {
        var netType = 0
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return netType
        val nType = networkInfo.type
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            val extraInfo = networkInfo.extraInfo
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase() == "cmnet") {
                    netType = NETTYPE_CMNET
                } else {
                    netType = NETTYPE_CMWAP
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI
        }
        return netType
    }

    companion object {

        val NETTYPE_WIFI = 0x0001
        val NETTYPE_CMWAP = 0x0002
        val NETTYPE_CMNET = 0x0003

        /**
         * 检测网络是否可用
         *
         * @return
         */
        fun isNetworkConnected(mContext: Context?): Boolean {
            val cm = mContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = cm.activeNetworkInfo
            return ni != null && ni.isConnectedOrConnecting
        }

        fun successResult(code: Int): Boolean {
            var successResult = false
            when (code) {
                ResultCode.SUCCESS, ResultCode.NO_DATA, ResultCode.TOKEN_LOSE, ResultCode.CANCEL_ORDER_FAIL -> successResult = true
            }
            return successResult
        }
    }
}
