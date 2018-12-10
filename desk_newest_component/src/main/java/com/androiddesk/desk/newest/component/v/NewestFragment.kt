package com.androiddesk.desk.newest.component.v

import android.os.Bundle
import androiddesk.com.desk.base.component.base.SimpleFragment
import com.androiddesk.desk.newest.component.R

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/10
 */
class NewestFragment : SimpleFragment() {

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
}