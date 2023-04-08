package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object SetPenaltyFeedback : SimpleCommand(
    Dialogue, "SetPenaltyFeedback", "设定违禁词反馈",
    description = "违禁词反馈语设定"
) {
    override val usage: String = "${CommandManager.commandPrefix}设定违禁词反馈 <反馈语>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(answer: String) {
        if (group.botMuteRemaining > 0) return
        DialogueData.groupConfiguration.getOrPut(group.id) {
            GroupSet(33, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf())
        }.penaltyFeedback.add(answer)
        sendMessage("添加成功")
    }
}