package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.isOwner


object TitleToApply: SimpleCommand(
    SelfHelpManagement, "TitleToApply", "申请头衔",
    description = "提供自助禁言功能"
) {
    override val usage = "${CommandManager.commandPrefix}申请头衔 <时间:分钟>\t#$description"


    @Handler
    suspend fun MemberCommandSenderOnMessage.main(title: String) {
        if (group.botMuteRemaining > 0) return
        if (group.botPermission.isOwner().not()) {
            sendMessage("权限不足吧")
            return
        }
        group[user.id]?.specialTitle = title
        sendMessage("操作已执行")
    }
}