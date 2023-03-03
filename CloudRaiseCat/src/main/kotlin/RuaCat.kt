package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object RuaCat : SimpleCommand(CloudRaiseCat, "RuaCat", "喂猫条", "吸猫", "rua猫", description = "吸猫") {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }
        val uhCat = userHome.cat
        if (uhCat == null) {
            sendMessage("铲屎官你还没有属于你的主子喔,快去买一只吧!")
            return
        }
        val r = uhCat.upDataCatInfo()
        if (r.isNullOrEmpty()) {
            when {
                uhCat.working() -> sendMessage("你的猫猫还在努力打工哦")
                userHome.liquid <= 0 -> sendMessage("铲屎官你家里已经没有猫条了")
                else -> { // 消耗一支猫条
                    uhCat.changeMood((10..20).random())
                    userHome.liquid -= 1
                    sendMessage("你给了 ${uhCat.name} 吃了一支猫条，狠狠的rua了猫猫")
                }
            }
            return
        }
        userHome.cat = null
        sendMessage(r)
    }
}