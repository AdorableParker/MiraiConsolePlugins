package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import java.time.LocalDateTime
import java.time.ZoneOffset

object ReceiveMLA : SimpleCommand(
    PersonalAccount, "ReceiveMLA", "领低保",
    description = "领每日低保"
) {
    override val usage: String = "${CommandManager.commandPrefix}领低保\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val userInfo = Account.user.getOrPut(user.id) { UserAccount(200, 0, 200, 0) }
        if(userInfo.gold < 500 && userInfo.loginTime < LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)/86400){
            userInfo.loginTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)/86400
            userInfo.gold += ((500 - userInfo.gold)/30).also { sendMessage("低保金${it}已到账") }
        }else sendMessage("不符和低保领取条件")

    }
}