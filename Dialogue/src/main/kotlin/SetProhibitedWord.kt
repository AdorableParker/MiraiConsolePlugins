package org.nymph

import net.mamoe.mirai.console.command.FriendCommandSenderOnMessage
import net.mamoe.mirai.console.command.GroupTempCommandSender
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.DialogueData.prohibitedWord


object SetProhibitedWord : SimpleCommand(
    Dialogue, "SetProhibitedWord", "设定违禁词",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(answer: String) {
        if (group.botMuteRemaining > 0) return

        prohibitedWord.add(answer)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun FriendCommandSenderOnMessage.main(answer: String) {
        prohibitedWord.add(answer)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun GroupTempCommandSender.main(answer: String) {
        prohibitedWord.add(answer)
        sendMessage("添加成功")
    }
}