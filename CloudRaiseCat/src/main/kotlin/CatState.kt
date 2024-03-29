package org.nymph

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.message.data.PlainText
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
        userHome.cat?.apply {
            upDataCatInfo()?.let {
                sendMessage(it)
                userHome.cat = null
            } ?: sendMessage(buildMessageChain {
                +(withContext(Dispatchers.IO) {
                    URL(picUrl).openConnection().getInputStream()
                }).uploadAsImage(group)
                +getCatInfo()
                +PlainText("\n拥有猫罐头: ${userHome.food}")
            })
        } ?: sendMessage("铲屎官你还没有属于你的主子喔,快去买一只吧!")
    }
}