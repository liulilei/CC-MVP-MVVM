package com.androiddesk

import android.os.Bundle
import androiddesk.com.desk.base.component.base.SimpleFragment
import com.androiddesk.base.component.bean.TabEntity
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.fragment_home.*
import me.yokeyword.fragmentation.SupportFragment
import java.util.*

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/6
 */
class HomeFragment : SimpleFragment() {

    private var currentFragment: SupportFragment? = null

    private var supportFragmentList = ArrayList<SupportFragment>()

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var categoryFrgament: SupportFragment? = null

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

    override fun initViewAndData() {
        fragmentHomeTl.setTabData(mTabEntities)
    }
}