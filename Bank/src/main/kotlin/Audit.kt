package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Audit : SimpleCommand(
    Bank, "Audit", "账户查询",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val account = BankVault.depositorMap[user.id]
        if (account == null){
            sendMessage("账户未开户")
            return
        }
        sendMessage("账户余额:${account.deposit}\n账户等级::${account.level+1}\n${10-account.days}天后收益结算")
    }
}