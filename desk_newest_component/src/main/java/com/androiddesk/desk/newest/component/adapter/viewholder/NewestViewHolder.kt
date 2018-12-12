package com.androiddesk.desk.newest.component.adapter.viewholder

import android.view.ViewGroup
import com.androiddesk.base.component.glide.ImageUtils
import com.androiddesk.desk.newest.component.R
import com.androiddesk.desk.newest.component.m.VerticalInfo
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_newest.view.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/12
 */
class NewestViewHolder(viewGroup: ViewGroup) : BaseViewHolder<VerticalInfo.Vertical>(viewGroup, R.layout.item_newest) {

    override fun setData(data: VerticalInfo.Vertical?) {
        ImageUtils.loadNormalImg(context, R.drawable.default_img, data?.thumb, itemView.imageView)
    }

}