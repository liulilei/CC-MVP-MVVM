package io.newdex.exchange.utils

import android.util.Log

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */
class LogUtils {

    companion object {
        var sDebug: Boolean = true

        fun setDebug(debug: Boolean) {
            sDebug = debug
        }

        fun d(text: String) {
            if (!sDebug) {
                return
            }
            d("temp", text)
        }

        fun d(tag: String?, text: String?) {
            if (!sDebug) {
                return
            }
            if (tag == null || text == null) {
                e(tag, text)
            }
            Log.println(Log.DEBUG, tag, text)
        }

        fun e(tag: String?, text: String?) {
            var tag = tag
            var text = text
            if (!sDebug) {
                return
            }
            if (tag == null) {
                tag = "@null"
            }
            if (text == null) {
                text = "@null"
            }
            Log.println(Log.ERROR, tag, text)
        }

        fun url(text: String) {
            if (sDebug) {
                d("url", text)
            }
        }

        fun value(text: String) {
            if (sDebug) {
                d("value", text)
            }
        }

    }
}