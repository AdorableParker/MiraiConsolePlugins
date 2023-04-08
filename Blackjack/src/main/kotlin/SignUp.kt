package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object SignUp : SimpleCommand(
    Blackjack, "SignUp", "报名",
    description = "投注报名参加"
) {
    override val usage: String = "${CommandManager.commandPrefix}报名 [投注金额]\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val table = BlackjackData.table[group.id]

        when (table?.gameState) {
            null, GameState.Closure -> sendMessage("游戏尚未开局,请使用开局命令开始游戏")
            GameState.Processing -> sendMessage("游戏已经开始了,等下一局吧")
            GameState.CanRegister, GameState.CanStarted -> sendMessage(table.signUp(user.id, 100))
        }
    }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(bet: Int) {
        if (group.botMuteRemaining > 0) return

        if (bet !in 100..1000) {
            sendMessage("最小投注为100,最大投注为1000")
            return
        }
        if (Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }.gold < bet) {
            sendMessage("您的现金不足以投注")
            return
        }

        val table = BlackjackData.table[group.id]
        when (table?.gameState) {
            null, GameState.Closure -> sendMessage("游戏尚未开局,请使用开局命令开始游戏")
            GameState.Processing -> sendMessage("游戏已经开始了,等下一局吧")
            GameState.CanRegister, GameState.CanStarted -> sendMessage(table.signUp(user.id, bet))
        }
    }
}
