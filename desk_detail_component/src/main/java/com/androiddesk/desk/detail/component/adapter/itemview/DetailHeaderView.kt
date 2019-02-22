package com.androiddesk.desk.detail.component.adapter.itemview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androiddesk.desk.detail.component.R
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 *@Description:
 * @author: lll
 * @date: 2019/2/20
 */
class DetailHeaderView : RecyclerArrayAdapter.ItemView {

    override fun onCreateView(parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.header_detail, null)
        return view
    }

    override fun onBindView(headerView: View?) {

    }

}