package org.nymph

import net.mamoe.mirai.console.command.*
import org.nymph.DialogueData.penaltyFeedback

object SetPenaltyFeedback : SimpleCommand(
    Dialogue, "SetPenaltyFeedback", "设定违禁词反馈",
    description = "违禁词反馈语设定"
) {
    override val usage: String = "${CommandManager.commandPrefix}设定违禁词反馈 <反馈语>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(answer: String) {
        if (group.botMuteRemaining > 0) return

        penaltyFeedback.add(answer)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun FriendCommandSenderOnMessage.main(answer: String) {
        penaltyFeedback.add(answer)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun GroupTempCommandSender.main(answer: String) {
        penaltyFeedback.add(answer)
        sendMessage("添加成功")
    }
}