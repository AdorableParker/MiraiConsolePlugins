package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Stand : SimpleCommand(
    Blackjack, "Stand", "停牌",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val table = BlackjackData.table[group.id]

        when (table?.gameState) {
            null, GameState.Closure -> sendMessage("游戏尚未开始,请使用开局命令开始游戏")
            GameState.CanRegister,GameState.CanStarted -> sendMessage("还没发牌呢,使用发牌命令开始游戏")
            GameState.Processing -> {
                if (user.id != table.playerList[table.next].playerID) sendMessage("还没轮到你行动呢")
                else sendMessage(table.stop())
            }
        }
    }
}