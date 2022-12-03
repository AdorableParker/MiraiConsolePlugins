package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.isOperator
import org.nymph.DynamicPushSetting.BotID

object SetBotID  : SimpleCommand(
    DynamicPush, "SetBotID", "设定BotID",
    description = "设定BotID"
) {
    override val usage: String = "${CommandManager.commandPrefix}设定BotID [BotID]"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(bid: Long) {
        if (user.isOperator()){
            BotID = bid
            sendMessage("设定完成")
        } else {
            sendMessage("权限不足")
        }
    }
}