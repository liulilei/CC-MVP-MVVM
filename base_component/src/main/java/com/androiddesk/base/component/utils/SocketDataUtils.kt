package io.newdex.exchange.utils

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import io.newdex.exchange.R
import io.newdex.exchange.app.BaseApplication
import io.newdex.exchange.constants.Constants
import io.newdex.exchange.constants.SocketConstants
import io.newdex.exchange.constants.SpConstants
import io.newdex.exchange.dialog.AssetQuitAccountDialog
import io.newdex.exchange.wswebsocket.WsManagerHelper
import io.newdex.exchange.wswebsocket.m.MarketsEosData
import io.newdex.exchange.wswebsocket.m.SendBindAccountBean
import io.newdex.exchange.wswebsocket.m.SendRequestBean
import io.newdex.exchange.wswebsocket.m.SendSubscribeBean
import java.math.BigDecimal
import java.util.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/27
 */
class SocketDataUtils {

    companion object {
        private fun swap(list: ArrayList<MarketsEosData>, index1: Int, index2: Int) {
            val maxIndex = list.size - 1
            val minIndex = 0
            if (index1 < minIndex || index1 > maxIndex) throw IndexOutOfBoundsException()
            if (index2 < minIndex || index2 > maxIndex) throw IndexOutOfBoundsException()
            val tmp = list[index1]
            list[index1] = list[index2]
            list[index2] = tmp
        }

        fun bubbleSort(type: Int, list: ArrayList<MarketsEosData>) {
            when (type) {
                SocketConstants.SORT_DEFAULT -> bubbleSortDefaultDesc(list)
                SocketConstants.INCREASE_DESC -> bubbleSortRiseDesc(list)
                SocketConstants.INCREASE_ACS -> bubbleSortRiseAcs(list)
                SocketConstants.PRICE_DESC -> bubbleSortPriceDesc(list)
                SocketConstants.PRICE_ACS -> bubbleSortPriceAcs(list)
                SocketConstants.VOLUME_DESC -> bubbleSortVolumeDesc(list)
                SocketConstants.VOLUME_ACS -> bubbleSortVolumeAcs(list)
                SocketConstants.LETTER_DESC -> sortLetterDesc(list)
                SocketConstants.LETTER_ACS -> sortLetterAcs(list)
            }
        }

        fun bubbleSortRiseDesc(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            val maxIndex = list.size - 1
            for (n in 0 until maxIndex) {
                for (i in 0 until maxIndex - n) {
                    if (list[i].change < list[i + 1].change) {
                        swap(list, i, i + 1)
                    }
                }
            }
        }

        fun bubbleSortRiseAcs(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            val maxIndex = list.size - 1
            for (n in 0 until maxIndex) {
                for (i in 0 until maxIndex - n) {
                    if (list[i].change > list[i + 1].change) {
                        swap(list, i, i + 1)
                    }
                }
            }
        }

        fun bubbleSortPriceDesc(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            val maxIndex = list.size - 1
            for (n in 0 until maxIndex) {
                for (i in 0 until maxIndex - n) {
                    if (list[i].price < list[i + 1].price) {
                        swap(list, i, i + 1)
                    }
                }
            }
        }

        fun bubbleSortPriceAcs(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            val maxIndex = list.size - 1
            for (n in 0 until maxIndex) {
                for (i in 0 until maxIndex - n) {
                    if (list[i].price > list[i + 1].price) {
                        swap(list, i, i + 1)
                    }
                }
            }
        }

        fun bubbleSortDefaultDesc(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            val maxIndex = list.size - 1
            for (n in 0 until maxIndex) {
                for (i in 0 until maxIndex - n) {
                    if (list[i].volume < list[i + 1].volume) {
                        swap(list, i, i + 1)
                    }
                }
            }
        }

        fun bubbleSortVolumeDesc(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            val maxIndex = list.size - 1
            for (n in 0 until maxIndex) {
                for (i in 0 until maxIndex - n) {
                    if (list[i].amount < list[i + 1].amount) {
                        swap(list, i, i + 1)
                    }
                }
            }
        }

        fun bubbleSortVolumeAcs(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            val maxIndex = list.size - 1
            for (n in 0 until maxIndex) {
                for (i in 0 until maxIndex - n) {
                    if (list[i].amount > list[i + 1].amount) {
                        swap(list, i, i + 1)
                    }
                }
            }
        }

        fun sortLetterDesc(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            try {
                list.sortWith(Comparator { o1, o2 ->
                    //重点是这个函数
                    //忽略掉大小写后,进行字符串的比较

                    val s1 = o1.symbol.split("-")[1].toLowerCase()
                    val s2 = o2.symbol.split("-")[1].toLowerCase()
                    s1.compareTo(s2)//从a-z的排
                })
            } catch (e: Exception) {

            }
        }

        fun sortLetterAcs(list: ArrayList<MarketsEosData>) {
            if (list.size == 0) return
            try {
                list.sortWith(Comparator { o1, o2 ->
                    //重点是这个函数
                    //忽略掉大小写后,进行字符串的比较
                    val s1 = o1.symbol.split("-")[1].toLowerCase()
                    val s2 = o2.symbol.split("-")[1].toLowerCase()
                    s2.compareTo(s1)//从z-a的排
                })
            } catch (e: Exception) {

            }
        }

        fun getSymbol(text: String?): String {
            try {
                if (!TextUtils.isEmpty(text)) {
                    val split = text?.split("-")!!
                    return (split[1] + "/" + split[2]).toUpperCase()
                }
            } catch (e: Exception) {

            }

            return ""
        }

        fun getCoin(text: String?): String {
            try {
                if (!TextUtils.isEmpty(text)) {
                    val split = text?.split("-")
                    return split!![1].toUpperCase()
                }
            } catch (e: Exception) {

            }

            return ""
        }

        fun getContract(text: String?): String {
            if (!TextUtils.isEmpty(text)) {
                val split = text?.split("-")
                return split!![0].toUpperCase()
            }
            return ""
        }

        fun getPriceDrawableResources(change: Double): Int {
            return when {
                change > 0 -> R.drawable.shape_up_down_green
                change < 0 -> R.drawable.shape_up_down_red
                else -> R.drawable.shape_up_down_gary
            }
        }

        fun getPriceColorResources(change: Double): Int {
            return when {
                change > 0 -> R.color.color_00BE66
                change < 0 -> R.color.color_EA573C
                else -> R.color.color_333333
            }
        }

        fun getDecimalString(decimalNum: Int?, targetValue: Double?): String {
            val bigDecimal = BigDecimal(targetValue!!)
            return bigDecimal.setScale(decimalNum!!, BigDecimal.ROUND_HALF_UP).toPlainString()
        }

        fun getDecimalDownString(decimalNum: Int?, targetValue: Double?): String {
            val bigDecimal = BigDecimal(targetValue!!)
            return bigDecimal.setScale(decimalNum!!, BigDecimal.ROUND_DOWN).toPlainString()
        }

        fun getDecimalBalanceString(decimalNum: Int?, targetValue: Double?): String {
            val bigDecimal = BigDecimal(targetValue!!)
            return if (targetValue == 0.0) {
                bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString()
            } else {
                bigDecimal.setScale(decimalNum!!, BigDecimal.ROUND_HALF_UP).toPlainString()
            }
        }

        fun getDecimalDownStringPrice(decimalNum: Int?, amount: Double?, price: Double?): String {
            var bigDecimal = BigDecimal(NumberUtil.mul(amount!!, price!!).toString())
            return bigDecimal.setScale(decimalNum!!, BigDecimal.ROUND_DOWN).toPlainString()
        }

        fun get24Vol(decimalNum: Int?, targetValue: Double?): String {
            return if (targetValue != null) {
                if (targetValue > Constants.MILLION) {
                    getDecimalString(0, targetValue)
                } else {
                    getDecimalString(decimalNum, targetValue)
                }
            } else {
                ""
            }
        }

        fun getChangeString(decimalNum: Int?, targetValue: Double?): String {
            if (targetValue != null) {
                return if (targetValue > 0) {
                    "+" + getDecimalString(decimalNum, targetValue!! * 100) + "%"
                } else getDecimalString(decimalNum, targetValue!! * 100) + "%"
            }
            return ""
        }

        fun showNormal(iv: ImageView, tv: TextView) {
            iv.setImageResource(R.mipmap.arrow_normal)
            tv.setTextColor(BaseApplication.getInstance()?.resources!!.getColor(R.color.color_9B9B9B))
        }

        fun showUp(iv: ImageView, tv: TextView) {
            iv.setImageResource(R.mipmap.arrow_sort_up)
            tv.setTextColor(BaseApplication.getInstance()?.resources!!.getColor(R.color.color_4A90E2))
        }

        fun showDown(iv: ImageView, tv: TextView) {
            iv.setImageResource(R.mipmap.arrow_sort_down)
            tv.setTextColor(BaseApplication.getInstance()?.resources!!.getColor(R.color.color_4A90E2))
        }

        fun getRequestKLineId(): Long {
            return SocketConstants.K_LINE_TYPE_ID + Random().nextInt(10000)
        }

        fun getXLabelTime(route: String, time: Long): String {
            var xLabel = ""
            when (route) {
            //socket请求k线route
                SocketConstants.KLINE_MINUTE -> xLabel = TimeUtils.getHourAndMinute(time * 1000)
                SocketConstants.KLINE_MINUTE_5 -> xLabel = TimeUtils.fullTimeAndDay(time * 1000)
                SocketConstants.KLINE_MINUTE_15 -> xLabel = TimeUtils.fullTimeAndDay(time * 1000)
                SocketConstants.KLINE_MINUTE_30 -> xLabel = TimeUtils.fullTimeAndDay(time * 1000)
                SocketConstants.KLINE_HOUR -> xLabel = TimeUtils.fullTimeAndDay(time * 1000)
                SocketConstants.KLINE_DAY -> xLabel = TimeUtils.fullTime(time * 1000)
                SocketConstants.KLINE_WEEK -> xLabel = TimeUtils.fullTime(time * 1000)
                SocketConstants.KLINE_MONTH -> xLabel = TimeUtils.fullTime(time * 1000)
            }
            return xLabel
        }

        fun getNextId(route: String, time: Long): Long {
            var nextId = time * 1000
            when (route) {
            //socket请求k线route
                SocketConstants.KLINE_MINUTE -> nextId += TimeUtils.M
                SocketConstants.KLINE_MINUTE_5 -> nextId += TimeUtils.M * 5
                SocketConstants.KLINE_MINUTE_15 -> nextId += TimeUtils.M * 15
                SocketConstants.KLINE_MINUTE_30 -> nextId += TimeUtils.M * 30
                SocketConstants.KLINE_HOUR -> nextId += TimeUtils.H
                SocketConstants.KLINE_DAY -> nextId += TimeUtils.D
                SocketConstants.KLINE_WEEK -> nextId += TimeUtils.D * 7
                SocketConstants.KLINE_MONTH -> nextId += TimeUtils.D * 30
            }
            return nextId / 1000
        }

        fun requestSubscribeK(id: Long, route: String, symbol: String) {
            var time: Long = System.currentTimeMillis()
            val params = SendRequestBean.Params(symbol.toLowerCase(), getFromTime(route, time) / 1000, time / 1000)
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendRequestBean(SocketConstants.REQUEST, id, route, params)))
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.SUBSCRIBE, route + "." + symbol.toLowerCase())))
        }

        fun unSubscribeK(route: String, symbol: String) {
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.UN_SUBSCRIBE, route + "." + symbol.toLowerCase())))
        }

        private fun getFromTime(route: String, to: Long): Long {
            var from = 0L
            when (route) {
            //socket请求k线route
                SocketConstants.KLINE_MINUTE -> from = TimeUtils.M
                SocketConstants.KLINE_MINUTE_5 -> from = TimeUtils.M * 5
                SocketConstants.KLINE_MINUTE_15 -> from = TimeUtils.M * 15
                SocketConstants.KLINE_MINUTE_30 -> from = TimeUtils.M * 30
                SocketConstants.KLINE_HOUR -> from = TimeUtils.H
                SocketConstants.KLINE_DAY -> from = TimeUtils.D
                SocketConstants.KLINE_WEEK -> from = TimeUtils.D * 7
                SocketConstants.KLINE_MONTH -> from = TimeUtils.D * 30
            }
            return to - (from * SocketConstants.RESPONSE_COUNT)
        }

        fun subscribeTrade(symbol: String) {
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.SUBSCRIBE, SocketConstants.DEPTH + "." + symbol.toLowerCase())))
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.SUBSCRIBE, SocketConstants.TICKER + "." + symbol.toLowerCase())))
        }

        fun unSubscribeTrade(symbol: String) {
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.UN_SUBSCRIBE, SocketConstants.DEPTH + "." + symbol.toLowerCase())))
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.UN_SUBSCRIBE, SocketConstants.TICKER + "." + symbol.toLowerCase())))
        }

        fun getTradeStatusDialog(context: Context, titleId: Int, contentId: Int, closeTime: String): AssetQuitAccountDialog {
            var dialog = AssetQuitAccountDialog(context, context.getString(titleId), 1, null)
            dialog?.setContent(String.format(context.getString(contentId), closeTime))
//            dialog?.setContentInfo(context.getString(R.string.trade_type_dialog_info))
            return dialog
        }

        fun subscribeDepth(symbol: String) {
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.SUBSCRIBE, SocketConstants.DEPTH + "." + symbol.toLowerCase())))
        }

        fun unSubscribeDepth(symbol: String) {
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.UN_SUBSCRIBE, SocketConstants.DEPTH + "." + symbol.toLowerCase())))
        }

        fun subscribeAccount() {
            if (!TextUtils.isEmpty(SpUtils.getAuthAccount()) && !TextUtils.isEmpty(SpUtils.getString(SpConstants.AUTH_TOKEN, ""))) {
                val accountParams = SendBindAccountBean.AccountParams(SpUtils.getAuthAccount()!!)
                WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendBindAccountBean(SocketConstants.SUBSCRIBE, accountParams, SocketConstants.ACCOUNT_MESSAGE)))
            }
        }

        fun unSubscribeAccount() {
            if (!TextUtils.isEmpty(SpUtils.getAuthAccount()) && !TextUtils.isEmpty(SpUtils.getString(SpConstants.AUTH_TOKEN, ""))) {
                val accountParams = SendBindAccountBean.AccountParams(SpUtils.getAuthAccount()!!)
                WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendBindAccountBean(SocketConstants.UN_SUBSCRIBE, accountParams, SocketConstants.ACCOUNT_MESSAGE)))
            }
        }

        fun subscribeNewdeal(symbol: String) {
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.SUBSCRIBE, SocketConstants.NEWDEAL + "." + symbol.toLowerCase())))
        }

        fun unSubscribeNewdeal(symbol: String) {
            WsManagerHelper.getInstance()?.sendMessage(ParseDataUtils.toJson(SendSubscribeBean(SocketConstants.UN_SUBSCRIBE, SocketConstants.NEWDEAL + "." + symbol.toLowerCase())))
        }

    }

}