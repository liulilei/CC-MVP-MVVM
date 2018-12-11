package androiddesk.com.desk.base.component.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androiddesk.com.desk.base.component.R
import androiddesk.com.desk.base.component.module.ActivityModule
import com.androiddesk.base.component.utils.ActivityManager
import com.jaeger.library.StatusBarUtil
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator
import javax.inject.Inject

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/7
 */
abstract class BaseActivity<T : RxPresenter<*>> : SupportActivity(), BaseView {

    @Inject
    @JvmField
    var mPresenter: T? = null

    var mContext: Activity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartAnimation()
        setContentView(initLayout())
        StatusBarUtil.setTransparentForImageViewInFragment(this, null)
        mContext = this
        initInject()
        (mPresenter as RxPresenter<BaseView>)?.attachView(this)
        if (savedInstanceState == null) {
            loadRootFragment()
        }
        ActivityManager.getAppManager().addActivity(this)
        initViewAndData()
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
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
        mPresenter?.detachView()
        ActivityManager.getAppManager().finishActivity(this)
    }

    protected abstract fun initLayout(): Int

    protected abstract fun initInject()

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