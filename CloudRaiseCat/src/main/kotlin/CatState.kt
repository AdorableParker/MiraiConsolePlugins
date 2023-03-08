package org.nymph

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.net.URL

object CatState : SimpleCommand(
    CloudRaiseCat, "CatState", "猫猫状态", description = "查询猫猫状态"
) {
    override val usage: String = "${CommandManager.commandPrefix}猫猫状态 \t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }
        val uhCat = userHome.cat
        if (uhCat == null) {
            sendMessage("铲屎官你还没有属于你的主子喔,快去买一只吧!")
            return
        }
        sendMessage(buildMessageChain {
            +(withContext(Dispatchers.IO) {
                URL(uhCat.picUrl).openConnection().getInputStream()
            }).uploadAsImage(group)
            +uhCat.getCatInfo()
        })
    }
}