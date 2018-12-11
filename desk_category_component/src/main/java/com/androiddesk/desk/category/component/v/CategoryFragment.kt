package com.androiddesk.desk.category.component.v

import android.os.Bundle
import com.androiddesk.base.component.constants.Constants
import com.androiddesk.desk.category.component.R
import com.androiddesk.desk.category.component.p.CategoryPresenter
import com.androiddesk.desk.category.component.p.contract.CategoryContract

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/7
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryContract.View {

    private var pageNo = Constants.DEFAULT_PAGE_NO

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
    }

}