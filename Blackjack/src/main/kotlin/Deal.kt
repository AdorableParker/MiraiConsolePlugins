package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Deal : SimpleCommand(
    Blackjack, "Deal", "发牌",
    description = "发牌开始游戏"
) {
    override val usage: String = "${CommandManager.commandPrefix}发牌\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val table = BlackjackData.table[group.id]

        when (table?.gameState) {
            null, GameState.Closure -> sendMessage("游戏尚未开始,请使用开局命令开始游戏")
            GameState.CanRegister -> sendMessage("还没人报名呢,请使用报名命令报名加入")
            GameState.CanStarted, GameState.Processing -> sendMessage(table.deal())
        }
    }
}
