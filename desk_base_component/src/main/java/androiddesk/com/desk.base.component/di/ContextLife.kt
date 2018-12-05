package androiddesk.com.desk.base.component.di


import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Qualifier

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/6
 */

@Qualifier
@Documented
@Retention(RUNTIME)
annotation class ContextLife(val value: String = "Application")