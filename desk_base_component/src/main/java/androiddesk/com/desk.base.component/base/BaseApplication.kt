package androiddesk.com.desk.base.component.base

import android.app.Activity
import android.app.Application
import android.content.Context
import androiddesk.com.desk.base.component.BuildConfig
import androiddesk.com.desk.base.component.R
import androiddesk.com.desk.base.component.component.AppComponent
import androiddesk.com.desk.base.component.component.DaggerAppComponent
import androiddesk.com.desk.base.component.module.AppModule
import androiddesk.com.desk.base.component.net.DeskBaseInterceptor
import androiddesk.com.desk.base.component.net.NetApi
import com.androiddesk.base.component.net.RetrofitHelp
import com.androiddesk.base.component.utils.AppUtils
import com.androiddesk.base.component.utils.logger.TxtFormatStrategy
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.DiskLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.onAdaptListener
import me.yokeyword.fragmentation.Fragmentation

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/5
 */
class BaseApplication : Application() {
    companion object {
        var APP_VERSION_CODE: Int = 0

        var APP_VERSION_NAME: String = ""

        var DEVICE_IMEI: String = ""

        var MOBILE_TYPE: String = ""

        var SYSTEM_TYPE: String = ""

        var CHANNEL_NAME: String = ""

        var mInstance: BaseApplication? = null

        var component: AppComponent? = null

        var mContext: Context? = null

        fun getInstance(): BaseApplication? {
            return mInstance
        }

        fun getAppComponent(): AppComponent? {
            if (component == null) {
                component = DaggerAppComponent.builder()
                        .appModule(AppModule(mInstance!!))
                        .build()
            }
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (mInstance == null) {
            mInstance = this
        }
        mContext = applicationContext
        initAppParams(this)

        RetrofitHelp.setBaseUrl(NetApi.URL)
        RetrofitHelp.setInterceptor(DeskBaseInterceptor())

        AutoSizeConfig.getInstance()

                //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
                //如果没有这个需求建议不开启
                .setCustomFragment(true).onAdaptListener = object : onAdaptListener {
            override fun onAdaptBefore(target: Any?, activity: Activity?) {
            }

            override fun onAdaptAfter(target: Any?, activity: Activity?) {
            }

        }

        if (BuildConfig.DEBUG) {
            Fragmentation.builder()
                    // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                    .stackViewMode(Fragmentation.BUBBLE)
                    .debug(BuildConfig.DEBUG)
                    .install()

            Logger.addLogAdapter(AndroidLogAdapter(PrettyFormatStrategy.newBuilder().tag(getString(R.string.app_name)).build()))
        }

        //把log存到本地
        Logger.addLogAdapter(DiskLogAdapter(TxtFormatStrategy.newBuilder().tag(getString(R.string.app_name)).build(packageName, getString(R.string.app_name))));


    }

    private fun initAppParams(context: Context) {
        APP_VERSION_CODE = AppUtils.getAppVersionCode(context)
        APP_VERSION_NAME = AppUtils.getAppVersionName(context)
//        DEVICE_IMEI = AppUtils.getDeviceImei(context)
        MOBILE_TYPE = AppUtils.getDeviceName()
        SYSTEM_TYPE = AppUtils.getDeviceVersion()
        CHANNEL_NAME = AppUtils.getAppMetaData(context, "UMENG_CHANNEL")
    }

}