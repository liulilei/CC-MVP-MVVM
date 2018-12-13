package com.androiddesk.desk.newest.component.v

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.ViewGroup
import androiddesk.com.desk.base.component.utils.ViewUtils
import com.androiddesk.base.component.constants.Constants
import com.androiddesk.base.component.utils.StringUtils
import com.androiddesk.desk.newest.component.R
import com.androiddesk.desk.newest.component.adapter.viewholder.NewestViewHolder
import com.androiddesk.desk.newest.component.m.VerticalInfo
import com.androiddesk.desk.newest.component.p.NewestPresenter
import com.androiddesk.desk.newest.component.p.contract.NewestContract
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.jude.easyrecyclerview.decoration.SpaceDecoration
import kotlinx.android.synthetic.main.base_title.*
import kotlinx.android.synthetic.main.fragment_newest.*


/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class NewestFragment : BaseMvpFragment<NewestPresenter>(), NewestContract.View, RecyclerArrayAdapter.OnLoadMoreListener, RecyclerArrayAdapter.OnErrorListener {

    private var pageNo = Constants.DEFAULT_PAGE_NO

    private var adapter: RecyclerArrayAdapter<VerticalInfo.Vertical>? = null

    companion object {
        fun newInstance(): NewestFragment {
            val args = Bundle()
            val fragment = NewestFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initLayout(): Int {
        return R.layout.fragment_newest
    }

    override fun initInject() {
        getFragmentComponent().inject(this)
    }

    override fun initViewAndData() {
        titleTv.text = "最新"
        val gridLayoutManager = GridLayoutManager(context, 3)
        recyclerView.setLayoutManager(gridLayoutManager)
        val itemDecoration = SpaceDecoration(StringUtils.dip2px(context, 3f))//参数是距离宽度
        itemDecoration.setPaddingEdgeSide(true)//是否为左右2边添加padding.默认true.
        itemDecoration.setPaddingStart(true)//是否在给第一行的item添加上padding(不包含header).默认true.
        itemDecoration.setPaddingHeaderFooter(false)//是否对Header于Footer有效,默认false.
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.adapter = adapter.let {
            adapter = object : RecyclerArrayAdapter<VerticalInfo.Vertical>(context) {
                override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
                    return NewestViewHolder(parent)
                }
            }
            return@let adapter
        }
        gridLayoutManager.spanSizeLookup = adapter?.obtainGridSpanSizeLookUp(3)
        recyclerView.setRefreshListener { onRefresh() }
        ViewUtils.setEmptyView(recyclerView, "暂无记录")
        adapter?.setMore(R.layout.footer_loading_more, this)
        adapter?.setNoMore(R.layout.footer_nomore)
        adapter?.setError(R.layout.footer_error, this)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mPresenter?.getVerticalList(pageNo, true)
    }

    private fun onRefresh() {
        val pageNo = Constants.DEFAULT_PAGE_NO
        mPresenter?.getVerticalList(pageNo, false)
    }

    override fun onErrorShow() {
    }

    override fun onErrorClick() {
        adapter?.resumeMore()
    }

    override fun onLoadMore() {
        val pageNo = this.pageNo + 1
        mPresenter?.getVerticalList(pageNo, false)
    }

    override fun showList(vertical: List<VerticalInfo.Vertical>?, pageNo: Int) {
        this.pageNo = pageNo
        if (pageNo == Constants.DEFAULT_PAGE_NO) {
            adapter?.clear()
            recyclerView.isRefreshing = false
        }
        adapter?.addAll(vertical)
    }

    override fun showError(pageNo: Int) {
        if (pageNo == Constants.DEFAULT_PAGE_NO) {
            recyclerView.isRefreshing = false
        } else {
            adapter?.pauseMore()
        }
    }

}