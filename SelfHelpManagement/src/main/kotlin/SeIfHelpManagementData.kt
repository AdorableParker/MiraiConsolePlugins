package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object SeIfHelpManagementData: AutoSavePluginData("SeIfHelpManagementData") {
    val enableReportingList: MutableSet<Long> by value(mutableSetOf())


}