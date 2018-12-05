package io.newdex.exchange.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.text.TextUtils
import io.newdex.exchange.constants.Constants
import java.util.*

/**
 * @author YanLu
 * @since 17/5/12
 */

object AppLanguageUtils {

    val mAllLanguages = object : LinkedHashMap<String, Locale>(4) {
        init {
            put(Constants.LANGUAGE_EN, Locale.ENGLISH)
            put(Constants.LANGUAGE_ZH_CN, Locale.SIMPLIFIED_CHINESE)
            put(Constants.LANGUAGE_ZH_TW, Locale.TAIWAN)
            put(Constants.LANGUAGE_KO, Locale.KOREAN)
        }
    }

    fun changeAppLanguage(context: Context, newLanguage: String) {
        val resources = context.resources
        val configuration = resources.configuration

        // app locale
        val locale = getLocaleByLanguage(newLanguage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
        } else {
            configuration.locale = locale
        }

        // updateConfiguration
        val dm = resources.displayMetrics
        resources.updateConfiguration(configuration, dm)
    }


    private fun isSupportLanguage(language: String): Boolean {
        return mAllLanguages.containsKey(language)
    }

    //    public static String getSupportLanguage(String language) {
    //        if (isSupportLanguage(language)) {
    //            return language;
    //        }
    //
    //        return ConstantLanguages.LANGUAGE_EN;
    //    }

    /**
     * 获取指定语言的locale信息，如果指定语言不存在[.mAllLanguages]，返回本机语言，如果本机语言不是语言集合中的一种[.mAllLanguages]，返回英语
     *
     * @param language language
     * @return
     */
    fun getLocaleByLanguage(language: String): Locale? {
        if (isSupportLanguage(language)) {
            return mAllLanguages[language]
        } else {
            val locale = Locale.getDefault()
            for (key in mAllLanguages.keys) {
                if (TextUtils.equals(mAllLanguages[key]?.language, locale.language)) {
                    return locale
                }
            }
        }
        return Locale.ENGLISH
    }

    fun getLocaleByLanguage(): String? {
        val locale = Locale.getDefault()
        for (key in mAllLanguages.keys) {
            if (TextUtils.equals(mAllLanguages[key]?.language, locale.language)) {
                return key
            }
        }
        return Constants.LANGUAGE_EN
    }

    fun attachBaseContext(context: Context, language: String): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else {
            context
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val resources = context.resources
        val locale = AppLanguageUtils.getLocaleByLanguage(language)

        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.locales = LocaleList(locale)
        return context.createConfigurationContext(configuration)
    }

    //    public static String getSystemLanguage() {
    //        Locale locale;
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    //            locale = LocaleList.getDefault().get(0);
    //        } else {
    //            locale = Locale.getDefault();
    //        }
    //        return locale.getCurrentLanguage();
    //    }
}
