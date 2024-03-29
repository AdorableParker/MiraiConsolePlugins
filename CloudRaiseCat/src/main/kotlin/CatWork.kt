package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object CatWork : SimpleCommand(CloudRaiseCat, "CatWork", "猫猫打工", description = "让猫猫打工") {
    override val usage: String = "${CommandManager.commandPrefix}猫猫打工 \t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }
        val uhCat = userHome.cat
        if (uhCat == null) {
            sendMessage("铲屎官你还没有属于你的猫猫哦，快去猫店买一只吧")
            return
        }
        val r = uhCat.upDataCatInfo()
        if (r.isNullOrEmpty()) {
            if (uhCat.working()) sendMessage("你的猫猫还在努力打工哦") else sendMessage(uhCat.toWork())
            return
        }
        userHome.cat = null
        sendMessage(r)
    }
}