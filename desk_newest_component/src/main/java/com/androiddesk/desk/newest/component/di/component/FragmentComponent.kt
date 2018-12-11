package com.androiddesk.desk.newest.component.di.component

import android.app.Activity
import androiddesk.com.desk.base.component.component.AppComponent
import androiddesk.com.desk.base.component.di.FragmentScope
import androiddesk.com.desk.base.component.module.FragmentModule
import com.androiddesk.desk.newest.component.v.NewestFragment
import dagger.Component

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun getActivity(): Activity?

    fun inject(fragment: NewestFragment)

}