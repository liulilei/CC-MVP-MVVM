package com.androiddesk.desk.detail.component.mvvm.util

import java.lang.reflect.ParameterizedType

/**
 *@Description: 获取超类的泛型对象
 * @author: lll
 * @date: 2018/12/27
 */
object GenericSuperUtils {

    fun <T> getNewInstance(data: Any?, i: Int): T? {
        if (data != null) {
            try {
                return ((data.javaClass
                        .genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>)
                        .newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: ClassCastException) {
                e.printStackTrace()
            }

        }
        return null

    }

    fun <T> getInstance(data: Any?, i: Int): T? {
        return if (data != null) {
            (data.javaClass
                    .genericSuperclass as ParameterizedType)
                    .actualTypeArguments[i] as T
        } else null

    }

    fun <T> checkNotNull(reference: T?): T {
        if (reference == null) {
            throw NullPointerException()
        }
        return reference
    }
}