package io.newdex.exchange.module.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umeng.analytics.MobclickAgent
import io.newdex.exchange.app.BaseApplication
import io.newdex.exchange.di.component.DaggerFragmentComponent
import io.newdex.exchange.di.module.FragmentModule
import io.newdex.exchange.utils.UIUtils
import me.yokeyword.fragmentation.SupportFragment
import javax.inject.Inject

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/7
 */
abstract class BaseFragment< T : RxPresenter<*>> : SupportFragment(), BaseView {

    @Inject
    @JvmField
    var mPresenter: T? = null

    protected var rootView: View? = null

    protected var mActivity: Activity? = null

    protected var mContext: Context? = null

    protected var isInited = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(initLayout(), null)
        initInject()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (mPresenter as RxPresenter<BaseView>)?.attachView(this)
        initViewAndData()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        isInited = true
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as Activity?
        mContext = context
    }

    protected fun getFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build()
    }

    protected fun getFragmentModule(): FragmentModule {
        return FragmentModule(this)
    }


    override fun onDestroy() {
        UIUtils.hideInputKeyboard(mActivity)
        super.onDestroy()
        mPresenter?.detachView()
    }

    protected abstract fun initLayout(): Int

    protected abstract fun initInject()

    open fun initViewAndData() {}

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(this.javaClass.simpleName) //统计页面，类名为页面名称
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(this.javaClass.simpleName)
    }

}