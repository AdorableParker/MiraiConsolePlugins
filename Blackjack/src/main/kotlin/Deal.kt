package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Deal : SimpleCommand(
    Blackjack, "Deal", "发牌",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val table = BlackjackData.table[group.id]

        when (table?.gameState) {
            null, GameState.Closure -> sendMessage("游戏尚未开始,请使用开局命令开始游戏")
            GameState.CanRegister, GameState.Started -> sendMessage(table.deal())
        }
    }
}
