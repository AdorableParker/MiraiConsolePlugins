package org.nymph

import net.mamoe.mirai.console.command.*
import org.nymph.DialogueData.invalidProblemFeedback


object SetInvalidProblemFeedback : SimpleCommand(
    Dialogue, "SetInvalidProblemFeedback", "设定无回答反馈",
    description = "词库无回答反馈设定"
) {
    override val usage: String = "${CommandManager.commandPrefix}设定无回答反馈 <反馈语>\t#$description"
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