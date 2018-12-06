package com.androiddesk

import android.os.Bundle
import androiddesk.com.desk.base.component.base.SimpleFragment

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/6
 */
class HomeFragment : SimpleFragment() {

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initLayout(): Int {
        return R.layout.fragment_home
    }
}