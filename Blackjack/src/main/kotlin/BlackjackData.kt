package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object BlackjackData : AutoSavePluginData("BlackjackData") {
    val table: MutableMap<Long, Game21> by value()
}