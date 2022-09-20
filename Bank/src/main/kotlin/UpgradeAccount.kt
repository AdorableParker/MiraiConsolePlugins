package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object UpgradeAccount : SimpleCommand(
    Bank, "UpgradeAccount", "申请升级",
    description = "申请提升账户等级"
) {
    override val usage: String = "${CommandManager.commandPrefix}申请升级\t#$description"
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
        }else sendMessage("存款过低,申请驳回")
    }
}