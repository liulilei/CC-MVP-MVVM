package com.androiddesk.desk.newest.component.net

import com.androiddesk.desk.newest.component.m.VerticalInfo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
interface NewApi {

    @GET(NetApi.NEW_URL)
    fun getVerticalNew(@QueryMap params: Map<String, String>): Flowable<VerticalInfo>
}