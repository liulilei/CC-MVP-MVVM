package com.androiddesk.base.component.widget.swiperecycler

import android.support.v7.widget.RecyclerView
import com.androiddesk.base.component.constants.Constants
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

class EasyDataObserver(private val recyclerView: SwipeRefreshRecyclerView) : RecyclerView.AdapterDataObserver() {
    private var adapter: RecyclerArrayAdapter<*>? = null
    private val DEFAULT_EACH_PAGE_ITEM_COUNT = Constants.DEFAULT_PAGE_SIZE//默认每页加载条数为10
    private var mEachPageItemCount = Constants.DEFAULT_PAGE_SIZE

    init {
        if (recyclerView.adapter is RecyclerArrayAdapter<*>) {
            adapter = recyclerView.adapter as RecyclerArrayAdapter<*>
        }
    }

    constructor(recyclerView: SwipeRefreshRecyclerView, eachPageItemCount: Int) : this(recyclerView) {
        mEachPageItemCount = eachPageItemCount
    }

    private fun isHeaderFooter(position: Int): Boolean {
        return adapter != null && (position < adapter!!.headerCount || position >= adapter!!.headerCount + adapter!!.count)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        super.onItemRangeChanged(positionStart, itemCount)
        if (!isHeaderFooter(positionStart)) {
            update()
        }
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        if (!isHeaderFooter(positionStart)) {
            update()
        }
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        super.onItemRangeRemoved(positionStart, itemCount)
        if (!isHeaderFooter(positionStart)) {
            update()
        }

    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount)
        update()//header&footer不会有移动操作
    }

    override fun onChanged() {
        super.onChanged()
        update()//header&footer不会引起changed
    }


    //自动更改Container的样式
    private fun update() {
        val count: Int
        if (recyclerView.adapter is RecyclerArrayAdapter<*>) {
            count = (recyclerView.adapter as RecyclerArrayAdapter<*>).count
            //加载不是EACH_PAGE_COUNT整数倍,显示加载完成
            if (count % mEachPageItemCount != 0) {
                (recyclerView.adapter as RecyclerArrayAdapter<*>).stopMore()
            }
        } else {
            count = recyclerView.adapter.itemCount
        }
        if (count == 0) {
            recyclerView.showEmpty()
        } else {
            recyclerView.showRecycler()
        }
    }
}