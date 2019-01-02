package com.androiddesk.desk.detail.component.mvvm.base

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androiddesk.base.component.utils.UIUtils
import com.androiddesk.desk.detail.component.mvvm.util.GenericSuperUtils
import me.yokeyword.fragmentation.SupportFragment

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/28
 */
abstract class BaseFragment<T : BaseViewModel<*>> : SupportFragment() {

    var mViewModel: T? = null

    protected var rootView: View? = null

    protected var mActivity: Activity? = null

    protected var mContext: Context? = null

    protected var isInited = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(initLayout(), null)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = vMProviders(this, GenericSuperUtils.getInstance<Class<T>>(this, 0))
        initViewAndData()
    }

    private fun <T : ViewModel> vMProviders(fragment: BaseFragment<*>, modelClass: Class<T>?): T {
        return ViewModelProviders.of(fragment).get(modelClass!!)

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

    override fun onDestroy() {
        UIUtils.hideInputKeyboard(mActivity)
        super.onDestroy()
    }

    protected abstract fun initLayout(): Int

    open fun initViewAndData() {}

}