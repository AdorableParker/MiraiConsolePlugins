package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand


object Withdraw : SimpleCommand(
    Bank, "Withdraw", "取款",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(value: Int) {
        if (group.botMuteRemaining > 0) return

        val account = Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }

        account.gold += value
        when (withdraw(user.id, value)) {
            404 -> sendMessage("账户未开户")
            403 -> sendMessage("存款不足")
            200 -> sendMessage("取款完成")
        }
    }

    private fun withdraw(depositorID: Long, value: Int): Int {
        val account = BankVault.depositorMap.getOrElse(depositorID) { return 404 }
        if (account.deposit < value) return 403
        account.deposit -= value
        BankVault.depositorDeposit -= value
        return 200
    }
}