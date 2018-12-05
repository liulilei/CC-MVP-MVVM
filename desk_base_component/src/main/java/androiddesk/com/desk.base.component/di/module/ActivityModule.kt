package androiddesk.com.desk.base.component.module

import android.app.Activity
import android.content.Context
import androiddesk.com.desk.base.component.di.ActivityScope
import dagger.Module
import dagger.Provides

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityScope
    fun provideContext(): Context {
        return mActivity
    }
}