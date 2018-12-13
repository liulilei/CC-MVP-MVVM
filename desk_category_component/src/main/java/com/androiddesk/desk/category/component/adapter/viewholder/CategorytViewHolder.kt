package com.androiddesk.desk.category.component.adapter.viewholder

import android.view.ViewGroup
import com.androiddesk.base.component.glide.ImageUtils
import com.androiddesk.desk.category.component.R
import com.androiddesk.desk.category.component.m.CategoryInfo
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_category.view.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/12
 */
class CategorytViewHolder(viewGroup: ViewGroup) : BaseViewHolder<CategoryInfo.Category>(viewGroup, R.layout.item_category) {

    override fun setData(data: CategoryInfo.Category?) {
        ImageUtils.loadNormalImg(context, R.drawable.default_img, data?.cover, itemView.imageView)
        itemView.imageTv.text = if (data?.name?.isNotEmpty()!!) {
            data?.name
        } else {
            ""
        }
    }

}