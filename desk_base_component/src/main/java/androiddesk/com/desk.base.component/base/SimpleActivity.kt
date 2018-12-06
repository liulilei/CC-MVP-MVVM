package androiddesk.com.desk.base.component.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androiddesk.com.desk.base.component.R
import com.androiddesk.base.component.utils.ActivityManager
import com.jaeger.library.StatusBarUtil
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * Created by lll on 2017/2/16.
 * mvp activity基类
 */

abstract class SimpleActivity : SupportActivity() {

    protected var mContext: Activity? = null

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
        initViewAndData()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }

    override fun finish() {
        super.finish()
        initEndAnimation()
    }

    protected abstract fun initLayout(): Int

    open fun loadRootFragment() {

    }

    open fun initViewAndData() {}


    open fun initStartAnimation() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏锁定
        overridePendingTransition(R.anim.in_push_right_to_left, R.anim.in_stable)
    }

    open fun initEndAnimation() {
        overridePendingTransition(R.anim.in_stable, R.anim.out_push_left_to_right)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.getAppManager().removeActivity(this)
    }

}
