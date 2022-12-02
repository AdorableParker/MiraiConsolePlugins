package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object DynamicPushData : AutoSavePluginData("DynamicPushData") { // "name" 是保存的文件名 (不带后缀)

    @ValueDescription("历史动态时间戳")
    val timeStampOfDynamic: MutableMap<Int, Long> by value(
        mutableMapOf(
            233114659 to 1L,
            161775300 to 1L,
            233108841 to 1L,
            401742377 to 1L
        )
    )


    @ValueDescription("订阅清单")
    val subscriptionList: MutableMap<Int, MutableSet<Long>> by value(
        mutableMapOf(
            233114659 to mutableSetOf(),
            161775300 to mutableSetOf(),
            233108841 to mutableSetOf(),
            401742377 to mutableSetOf()
        )
    )
}