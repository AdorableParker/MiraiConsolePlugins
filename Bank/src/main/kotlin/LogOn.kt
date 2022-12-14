package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand


object LogOn : SimpleCommand(
    Bank, "LogOn", "注册",
    description = "注册账户"
) {
    override val usage: String = "${CommandManager.commandPrefix}注册\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        if (user.id in BankVault.depositorMap.keys) {
            sendMessage("已有账号")
            return
        }
        BankVault.depositorMap[user.id] = Depositor(0, 0, 0)
        sendMessage("注册完成")
    }
}