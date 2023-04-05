package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value


object SlotMachineData : AutoSavePluginData("SlotMachineData") {
    var jackpot: Int by value(5000)
}