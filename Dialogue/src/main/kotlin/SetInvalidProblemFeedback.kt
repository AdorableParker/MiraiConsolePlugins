package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand


object SetInvalidProblemFeedback : SimpleCommand(
    Dialogue, "SetInvalidProblemFeedback", "设定无回答反馈",
    description = "词库无回答反馈设定"
) {
    override val usage: String = "${CommandManager.commandPrefix}设定无回答反馈 <反馈语>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(answer: String) {
        if (group.botMuteRemaining > 0) return

        DialogueData.groupConfiguration.getOrPut(group.id) {
            GroupSet(33, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf())
        }.invalidProblemFeedback.add(answer)
        sendMessage("添加成功")
    }


}