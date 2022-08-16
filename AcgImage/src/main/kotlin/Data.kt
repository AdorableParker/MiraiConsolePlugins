package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object Data:AutoSavePluginData("Data") {
    var proxy:String by value("i.pixiv.re")
}