package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object CloudCatData : AutoSavePluginData("CloudCatData")  {
    val cloudCatList:MutableMap<Long,UserHome> by value(
        mutableMapOf()
    )
}