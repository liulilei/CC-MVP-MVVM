package com.androiddesk

import android.content.Intent
import androiddesk.com.desk.base.component.base.SimpleActivity

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/6
 */
class HomeActivity : SimpleActivity() {

    private var mIsForeground: Boolean = false

    override fun initLayout(): Int {
        return R.layout.activity_home
    }

    override fun initViewAndData() {
        mIsForeground = true
    }

    override fun loadRootFragment() {
        if (findFragment(HomeFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance())
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mIsForeground = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mIsForeground = false
    }
}