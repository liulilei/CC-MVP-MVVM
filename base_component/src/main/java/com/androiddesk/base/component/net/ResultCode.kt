package com.androiddesk.base.component.net


interface ResultCode {
    companion object {
        /* code */
        val SUCCESS = 0// 成功

        val ERROR = 1//操作失败

        val NO_DATA = 2//无数据

        val TOKEN_LOSE = 401// token失效

        val CANCEL_ORDER_FAIL = 1000 // 撤销订单失败
    }
}
