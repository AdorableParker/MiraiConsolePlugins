package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object JobData:AutoSavePluginData("jobData") {
    val timeAxis:MutableMap<Int,MutableList<JobEvent>> by value()
}