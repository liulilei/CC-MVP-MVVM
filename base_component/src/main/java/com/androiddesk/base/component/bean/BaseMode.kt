package com.androiddesk.base.component.bean

import io.newdex.exchange.module.base.BaseEntity

/**
 *@Description:
 * @author: lll
 * @date: 2018/8/7
 */
open class BaseMode<T> : BaseEntity {

    var code: Int = 0

    var msg: String? = null

    var res: T? = null

    constructor(code: Int) {
        this.code = code
    }

    constructor()

}