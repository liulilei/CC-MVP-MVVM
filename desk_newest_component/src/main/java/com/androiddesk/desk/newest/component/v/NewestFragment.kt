package com.androiddesk.desk.newest.component.v

import android.os.Bundle
import com.androiddesk.base.component.constants.Constants
import com.androiddesk.desk.newest.component.R
import com.androiddesk.desk.newest.component.m.VerticalInfo
import com.androiddesk.desk.newest.component.p.NewestPresenter
import com.androiddesk.desk.newest.component.p.contract.NewestContract

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class NewestFragment : BaseMvpFragment<NewestPresenter>(), NewestContract.View {

    private var pageNo = Constants.DEFAULT_PAGE_NO

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

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mPresenter?.getVerticalList(pageNo, true)
    }

    override fun showList(vertical: List<VerticalInfo.Vertical>?, pageNo: Int) {

    }

    override fun showError(pageNo: Int) {
    }

}