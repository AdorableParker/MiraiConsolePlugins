package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand


object Savings : SimpleCommand(
    Bank, "Savings", "存款",
    description = "存入现金"
) {
    override val usage: String = "${CommandManager.commandPrefix}存款 <金额>\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(value:Int) {
        if (group.botMuteRemaining > 0) return

        val account = Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }

        if (account.gold < value) {
            sendMessage("现金不足")
            return
        }

        account.gold -= value
        when(deposit(user.id, value)){
            404 -> sendMessage("账户未开户")
            200 -> sendMessage("存款完成")
        }
    }

    private fun deposit(depositorID: Long, value: Int):Int {
        val account = BankVault.depositorMap.getOrElse(depositorID){ return 404 }
        account.deposit += value
        BankVault.depositorDeposit += value
        return 200
    }
}