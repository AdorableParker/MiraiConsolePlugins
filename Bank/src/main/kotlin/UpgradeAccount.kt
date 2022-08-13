package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object UpgradeAccount : SimpleCommand(
    Bank, "UpgradeAccount", "申请升级",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val account = BankVault.depositorMap[user.id]
        if (account == null) {
            sendMessage("账户未开户")
            return
        }

        val gradeRange = when (account.deposit){
            in 0..1999 -> 0
            in 2000..4999 -> 1
            in 5000..9999 -> 2
            else -> 3
        }

        if (gradeRange < account.level){
            account.level++
            account.deposit -= account.level*1500
            sendMessage("申请批准")
        }else sendMessage("申请驳回")
    }
}