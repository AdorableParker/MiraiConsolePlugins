package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object BankVault : AutoSavePluginData("BankVault") {
    var depositorDeposit: Int by value(0)
    val depositorMap: MutableMap<Long, Depositor> by value(mutableMapOf())
}