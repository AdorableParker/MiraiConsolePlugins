package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object DynamicPushSetting : AutoSavePluginConfig("Setting") {

    @ValueDescription("手动查询动态UID简称列表")
    val DynamicNameList by value(
        mapOf(
            "碧蓝" to 233114659,
            "方舟" to 161775300,
            "FGO" to 233108841,
            "原神" to 401742377
        )
    )

    @ValueDescription("BotID")
    val BotID: Long by value()
}
