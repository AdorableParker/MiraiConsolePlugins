package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object DynamicPushSetting : AutoSavePluginConfig("Setting") {

    @ValueDescription("手动查询动态UID简称列表")
    val DynamicNameList by value(
        mapOf(
            "碧蓝" to 233114659L,
            "方舟" to 161775300L,
            "FGO" to 233108841L,
            "原神" to 401742377L
        )
    )

    @ValueDescription("BotID")
    var BotID: Long by value(0L)
}
