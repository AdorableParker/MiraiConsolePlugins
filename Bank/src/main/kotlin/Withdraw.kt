package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand


object Withdraw : SimpleCommand(
    Bank, "Withdraw", "取款",
    description = "取出存款"
) {
    override val usage: String = "${CommandManager.commandPrefix}取款 <金额>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(value: Int) {
        if (group.botMuteRemaining > 0) return

        val account = Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }

        sendMessage(when (withdraw(user.id, value)) {
            404 -> "账户未开户"
            403 -> "存款不足"
            200 -> {
                account.gold += value
                "取款完成"
            }
            else -> "系统异常"
        })
    }

    private fun withdraw(depositorID: Long, value: Int): Int {
        val bankAccount = BankVault.depositorMap.getOrElse(depositorID) { return 404 }
        if (bankAccount.deposit < value) return 403
        bankAccount.deposit -= value
        BankVault.depositorDeposit -= value
        return 200
    }
}