package com.androiddesk.desk.newest.component.v

import android.os.Bundle
import androiddesk.com.desk.base.component.base.SimpleFragment
import androiddesk.com.desk.base.component.base.WebViewFragment
import com.androiddesk.desk.my.component.R
import kotlinx.android.synthetic.main.base_title.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class MyFragment : SimpleFragment() {

    private var mWebViewFragment: WebViewFragment? = null

    private val url = "https://github.com/liulilei/CC-MVP-MVVM"

    companion object {
        fun newInstance(): MyFragment {
            val args = Bundle()
            val fragment = MyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initLayout(): Int {
        return R.layout.fragment_my
    }

    override fun initViewAndData() {
        titleTv.text = "我的"
        loadRootFragment(R.id.fl_container,
                mWebViewFragment.let {
                    mWebViewFragment = WebViewFragment.newInstance(url)
                    return@let mWebViewFragment
                })
    }

    override fun onBackPressedSupport(): Boolean {
        return if (mWebViewFragment != null && mWebViewFragment?.webViewGoBack()!!) {
            true
        } else super.onBackPressedSupport()
    }
}