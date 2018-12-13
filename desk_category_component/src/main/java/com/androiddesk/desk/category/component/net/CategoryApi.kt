package com.androiddesk.desk.category.component.net

import com.androiddesk.desk.category.component.m.CategoryInfo
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
interface CategoryApi {

    @GET(NetApi.CATEGORY_URL)
    fun getCategory(): Flowable<CategoryInfo>

}