package com.androiddesk

import android.Manifest
import android.os.Bundle
import androiddesk.com.desk.base.component.app.BaseApplication
import androiddesk.com.desk.base.component.base.SimpleFragment
import androiddesk.com.desk.base.component.processor.ComponentCategory
import androiddesk.com.desk.base.component.processor.ComponentMy
import androiddesk.com.desk.base.component.processor.ComponentNewest
import com.androiddesk.base.component.bean.TabEntity
import com.androiddesk.base.component.utils.NoDoubleClickUtils
import com.androiddesk.base.component.utils.ToastUtils
import com.billy.cc.core.component.CC
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_home.*
import me.yokeyword.fragmentation.SupportFragment
import java.util.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/6
 */
class HomeFragment : SimpleFragment() {

    private var supportFragmentList = ArrayList<SupportFragment>()

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var categoryFragment: SupportFragment? = null

    private var newestFragment: SupportFragment? = null

    private var myFragment: SupportFragment? = null

    private var disposable: Disposable? = null

    init {
        val mIconUnSelectIds = intArrayOf(R.mipmap.nav_home_normal, R.mipmap.nav_landscape_normal, R.mipmap.nav_person_normal)
        val mIconSelectIds = intArrayOf(R.mipmap.nav_home_selected, R.mipmap.nav_landscape_selected, R.mipmap.nav_person_selected)
        val titles = arrayOf("分类", "最新", "我的")
        for (i in titles.indices) {
            mTabEntities.add(TabEntity(titles[i], mIconSelectIds[i], mIconUnSelectIds[i]))
        }
    }

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var firstFragment = findChildFragment(CC.obtainBuilder(ComponentCategory.COMPONENT_NAME)
                .setActionName(ComponentCategory.CATEGORY_FRAGMENT_CLASS_ACTION)
                .build()
                .call()
                .getDataItem(ComponentCategory.CATEGORY_FRAGMENT_CLASS_DATA) as Class<SupportFragment>)
        if (firstFragment == null) {
            categoryFragment = CC.obtainBuilder(ComponentCategory.COMPONENT_NAME)
                    .setActionName(ComponentCategory.CATEGORY_FRAGMENT_ACTION)
                    .build()
                    .call()
                    .getDataItem(ComponentCategory.CATEGORY_FRAGMENT_DATA)
            newestFragment = getNewestFragment()
            myFragment = getMyFragment()
            loadMultipleRootFragment(R.id.fragment_home_fl_container, 0, categoryFragment, newestFragment, myFragment)
        } else {
            categoryFragment = firstFragment
            newestFragment = getNewestFragment()
            myFragment = getMyFragment()
        }

        supportFragmentList.add(categoryFragment!!)
        supportFragmentList.add(newestFragment!!)
        supportFragmentList.add(myFragment!!)
    }

    private fun getMyFragment(): SupportFragment? {
        return CC.obtainBuilder(ComponentMy.COMPONENT_NAME)
                .setActionName(ComponentMy.MY_FRAGMENT_ACTION)
                .build()
                .call()
                .getDataItem(ComponentMy.MY_FRAGMENT_DATA)
    }

    private fun getNewestFragment(): SupportFragment? {
        return CC.obtainBuilder(ComponentNewest.COMPONENT_NAME)
                .setActionName(ComponentNewest.NEWEST_FRAGMENT_ACTION)
                .build()
                .call()
                .getDataItem(ComponentNewest.NEWEST_FRAGMENT_DATA)
    }

    override fun initViewAndData() {
        initPermission()
        fragmentHomeTl.setTabData(mTabEntities)
        fragmentHomeTl.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                showHideFragment(supportFragmentList[position])
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    private fun initPermission() {
        disposable = RxPermissions(mActivity!!)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe()
    }

    override fun onBackPressedSupport(): Boolean {
        return if (!NoDoubleClickUtils.isDoubleClick()) {
            ToastUtils.show(BaseApplication.getInstance()!!, "再按一次退出程序")
            true
        } else {
            super.onBackPressedSupport()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable?.isDisposed!!) {
            disposable?.dispose()
        }
    }
}