package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.nameCardOrNick


object AccountInfo : SimpleCommand(
    PersonalAccount, "AccountInfo", "我的信息",
    description = "用户信息"
) {
    override val usage: String = "${CommandManager.commandPrefix}我的信息\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val userInfo = Account.user.getOrPut(user.id) { UserAccount(200, 0, 200, 0) }

        sendMessage("${user.nameCardOrNick}\n|金币:${userInfo.gold}枚\n|魔方:${userInfo.cube}个\n|好感度:${userInfo.favorAbility}‰")
    }
}