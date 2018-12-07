package com.androiddesk.desk.category.component.v

import android.os.Bundle
import androiddesk.com.desk.base.component.base.SimpleFragment
import com.androiddesk.desk.category.component.R

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/7
 */
class CategoryFragment : SimpleFragment() {

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
}