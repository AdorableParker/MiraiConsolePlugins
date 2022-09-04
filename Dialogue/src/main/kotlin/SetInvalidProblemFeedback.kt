package org.nymph

import net.mamoe.mirai.console.command.FriendCommandSenderOnMessage
import net.mamoe.mirai.console.command.GroupTempCommandSender
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.DialogueData.invalidProblemFeedback


object SetInvalidProblemFeedback : SimpleCommand(
    Dialogue, "SetInvalidProblemFeedback", "设定无回答反馈",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(answer: String) {
        if (group.botMuteRemaining > 0) return

        invalidProblemFeedback.add(answer)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun FriendCommandSenderOnMessage.main(answer: String) {
        invalidProblemFeedback.add(answer)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun GroupTempCommandSender.main(answer: String) {
        invalidProblemFeedback.add(answer)
        sendMessage("添加成功")
    }
}