package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object GaG : SimpleCommand(
    SelfHelpManagement, "Ga", "自助禁言",
    description = "提供自助禁言功能"
) {
    override val usage = "${CommandManager.commandPrefix}自助禁言 <时间:分钟>\t#$description"


    @Handler
    suspend fun MemberCommandSenderOnMessage.main(time: Int) {
        if (group.botMuteRemaining > 0) return
        if (group.botPermission <= user.permission){
            sendMessage("权限不足吧")
            return
        }
        user.mute(time * 60)
        sendMessage("操作已执行")
    }

}