package com.androiddesk.base.component.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/7
 */
open class AutoInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()
//        if (request?.url().toString().contains(NetApi.URL)) {
//            val newBuilder = request?.newBuilder()
//            newBuilder?.addHeader("language", SpUtils.getString(SpConstants.LANGUAGE_TYPE, Constants.LANGUAGE_DEFAULT))
//            newBuilder?.addHeader("channel", Constants.SYSTEM_TYPE)
//            if (!TextUtils.isEmpty(SpUtils.getString(SpConstants.AUTH_TOKEN, "")!!)) {
//                newBuilder?.addHeader("token", SpUtils.getString(SpConstants.AUTH_TOKEN, "")!!)
//            }
//            if (!TextUtils.isEmpty(SpUtils.getAuthAccount())) {
//                newBuilder?.addHeader("account", SpUtils.getAuthAccount())
//            }
//            request = newBuilder?.build()
//            val originalResponse = chain?.proceed(request)
//            //code返回401
//            var source = originalResponse?.body()?.source()
//            source?.request(Long.MAX_VALUE)
//            var buffer = source?.buffer()
//            var responseBodyString = buffer?.clone()?.readString(Charset.forName("UTF-8"))
//            LogUtils.e("originalResponse", responseBodyString)
//            return if (ParseDataUtils.parseGSON(responseBodyString, BaseMode::class.java).code == ResultCode.TOKEN_LOSE) {
//                SpUtils.putString(SpConstants.AUTH_TOKEN, "")
//                SpUtils.putAuthAccount("")
//                RxBus.getDefault().post(TokenLoseEvent(true))
//                val newBuilder1 = request?.newBuilder()
//                newBuilder1?.removeHeader("token")
//                newBuilder1?.removeHeader("account")
//                request = newBuilder1?.build()
//                chain?.proceed(request)
//            } else {
//                originalResponse
//            }
//        }
        return chain?.proceed(request)
    }

}