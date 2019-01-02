package com.androiddesk.desk.detail.component.mvvm.base

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androiddesk.com.desk.base.component.R
import com.androiddesk.base.component.utils.ActivityManager
import com.androiddesk.desk.detail.component.mvvm.util.GenericSuperUtils
import com.jaeger.library.StatusBarUtil
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/28
 */
abstract class BaseActivity<T : BaseViewModel<*>> : SupportActivity() {

    var mContext: Activity? = null

    var mViewModel: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartAnimation()
        setContentView(initLayout())
        StatusBarUtil.setTransparentForImageViewInFragment(this, null)
        mContext = this
        if (savedInstanceState == null) {
            loadRootFragment()
        }
        ActivityManager.getAppManager().addActivity(this)
        mViewModel = vMProviders(this, GenericSuperUtils.getInstance<Class<T>>(this, 0))
        initViewAndData()
        StatusBarUtil.setLightMode(this)
    }

    private fun <T : ViewModel> vMProviders(activity: AppCompatActivity, modelClass: Class<T>?): T {
        return ViewModelProviders.of(activity).get<T>(modelClass!!)
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }

    override fun finish() {
        super.finish()
        initEndAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.getAppManager().finishActivity(this)
    }

    protected abstract fun initLayout(): Int

    fun loadRootFragment() {

    }

    open fun initViewAndData() {}

    open fun initStartAnimation() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        overridePendingTransition(R.anim.in_push_right_to_left, R.anim.in_stable)
    }

    open fun initEndAnimation() {
        overridePendingTransition(R.anim.in_stable, R.anim.out_push_left_to_right)
    }


}