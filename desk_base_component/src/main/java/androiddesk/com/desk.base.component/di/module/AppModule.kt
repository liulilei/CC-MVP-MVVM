package androiddesk.com.desk.base.component.module

import androiddesk.com.desk.base.component.app.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */

@Module
class AppModule(private val application: BaseApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): BaseApplication {
        return application
    }

}