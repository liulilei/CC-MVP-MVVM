package com.androiddesk.desk.category.component.net

import com.androiddesk.base.component.net.RetrofitHelp

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
class ApiManager {
    
    companion object {

        private var newApi: CategoryApi? = null

        fun getNewApi(): CategoryApi? {
            if (newApi == null) {
                synchronized(CategoryApi::class.java) {
                    if (newApi == null) {
                        newApi = RetrofitHelp.getRetrofit()?.create(CategoryApi::class.java)
                    }
                }
            }
            return newApi
        }
    }
}