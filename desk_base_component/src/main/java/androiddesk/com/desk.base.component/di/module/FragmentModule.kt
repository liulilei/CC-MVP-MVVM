package androiddesk.com.desk.base.component.module

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import androiddesk.com.desk.base.component.di.FragmentScope
import dagger.Module
import dagger.Provides

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideActivity(): Activity? {
        return fragment.activity
    }

    @Provides
    @FragmentScope
    fun provideContext(): Context? {
        return fragment.context
    }
}