package com.androiddesk.desk.category.component.v

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.ViewGroup
import com.androiddesk.base.component.utils.StringUtils
import com.androiddesk.desk.category.component.R
import com.androiddesk.desk.category.component.adapter.viewholder.CategorytViewHolder
import com.androiddesk.desk.category.component.m.CategoryInfo
import com.androiddesk.desk.category.component.p.CategoryPresenter
import com.androiddesk.desk.category.component.p.contract.CategoryContract
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.jude.easyrecyclerview.decoration.SpaceDecoration
import kotlinx.android.synthetic.main.base_title.*
import kotlinx.android.synthetic.main.fragment_category.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/7
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryContract.View {

    private var adapter: RecyclerArrayAdapter<CategoryInfo.Category>? = null

    companion object {
        fun newInstance(): CategoryFragment {
            val args = Bundle()
            val fragment = CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initLayout(): Int {
        return R.layout.fragment_category
    }

    override fun initInject() {
        getFragmentComponent().inject(this)
    }

    override fun initViewAndData() {
        titleTv.text = "分类"
        val gridLayoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = gridLayoutManager
        val itemDecoration = SpaceDecoration(StringUtils.dip2px(context, 5f))//参数是距离宽度
        itemDecoration.setPaddingEdgeSide(true)//是否为左右2边添加padding.默认true.
        itemDecoration.setPaddingStart(true)//是否在给第一行的item添加上padding(不包含header).默认true.
        itemDecoration.setPaddingHeaderFooter(false)//是否对Header于Footer有效,默认false.
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = adapter.let {
            adapter = object : RecyclerArrayAdapter<CategoryInfo.Category>(context) {
                override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
                    return CategorytViewHolder(parent)
                }
            }
            return@let adapter
        }
        gridLayoutManager.spanSizeLookup = adapter?.obtainGridSpanSizeLookUp(3)
        mPresenter?.getCategoryList()
    }

    override fun showList(vertical: List<CategoryInfo.Category>?) {
        adapter?.clear()
        adapter?.addAll(vertical)
    }

}