package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object GroupWifeData : AutoSavePluginData("GroupWifeData") {
    val wifeGroupMap: MutableMap<Long, GroupData> by value()
    var groupWifeUpdate = 0


    fun cleanList(today:Int ) {
        groupWifeUpdate = today
        wifeGroupMap.forEach {
            it.value.clear()
        }
    }
}



