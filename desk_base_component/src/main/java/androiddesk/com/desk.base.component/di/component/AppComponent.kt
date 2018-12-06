package androiddesk.com.desk.base.component.component

import androiddesk.com.desk.base.component.app.BaseApplication
import androiddesk.com.desk.base.component.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    // 提供App的Context
    fun getContext(): BaseApplication
}