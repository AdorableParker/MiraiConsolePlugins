package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object Data : AutoSavePluginData("data") {
    @ValueDescription("初始化状态,如果为True则会初始化重置所有用户数据")
    var initialization: Boolean by value(true)

    @ValueDescription("初始化状态,如果为True则会初始化重置所有用户数据")
    var version: Int by value(0)
}

