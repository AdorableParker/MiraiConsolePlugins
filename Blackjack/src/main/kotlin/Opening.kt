package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Opening : SimpleCommand(
    Blackjack, "Opening", "玩21点",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val table = BlackjackData.table.getOrPut(group.id) { Game21() }
        when (table.gameState) {
            GameState.Closure -> {
                table.init()
                sendMessage("21点开始报名,请使用报名命令报名入局,使用发牌命令开始游戏")
            }
            GameState.CanRegister, GameState.CanStarted -> sendMessage("本局游戏已经开始报名了")
            GameState.Processing -> sendMessage("游戏已经开始了,等下一局吧")
        }
    }
}