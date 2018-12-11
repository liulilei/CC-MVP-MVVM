package com.androiddesk.desk.newest.component.net

import com.androiddesk.base.component.net.RetrofitHelp

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
class ApiManager {
    
    companion object {

        private var newApi: NewApi? = null

        fun getNewApi(): NewApi? {
            if (newApi == null) {
                synchronized(NewApi::class.java) {
                    if (newApi == null) {
                        newApi = RetrofitHelp.getRetrofit()?.create(NewApi::class.java)
                    }
                }
            }
            return newApi
        }
    }
}