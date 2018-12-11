package com.androiddesk.desk.newest.component.m

import com.androiddesk.base.component.bean.BaseMode

/**
 *@Description:
 * @author: lll
 * @date: 2018/12/11
 */
class VerticalInfo : BaseMode<VerticalInfo.VerticalList>() {
    data class VerticalList(var vertical: List<Vertical>)
    data class Vertical(
            var views: Int,
            var ncos: Int,
            var rank: Int,
            var tag: List<String>,
            var wp: String,
            var xr: Boolean,
            var cr: Boolean,
            var favs: Int,
            var atime: Int,
            var id: String,
            var desc: String,
            var thumb: String,
            var img: String,
            var cid: List<String>,
            var url: List<String>,
            var rule: String,
            var preview: String,
            var store: String
    )
}