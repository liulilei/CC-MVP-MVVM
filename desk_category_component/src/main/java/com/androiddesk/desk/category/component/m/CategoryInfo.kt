package com.androiddesk.desk.category.component.m

import com.androiddesk.base.component.bean.BaseMode

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/12
 */
class CategoryInfo : BaseMode<CategoryInfo.CategoryList>() {
    data class CategoryList(var category: List<Category>)
    data class Category(
            val ename: String,
            val atime: Int,
            val name: String,
            val cover: String,
            val sn: Int,
            val nimgs: Int,
            val uid: String,
            val type: Int,
            val id: String,
            val desc: String
    )
}