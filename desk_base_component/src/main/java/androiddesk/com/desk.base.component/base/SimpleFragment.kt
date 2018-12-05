package io.newdex.exchange.module.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by lll on 2017/2/16.
 * 无mvp fragment基类
 */

abstract class SimpleFragment : SupportFragment() {

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

    protected abstract fun initLayout(): Int

    open fun initViewAndData() {}

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}
