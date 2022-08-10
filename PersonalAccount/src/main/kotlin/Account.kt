package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object Account : AutoSavePluginData("account") {
    val user: MutableMap<Long, UserAccount> by value(
        mutableMapOf()
    )
}
