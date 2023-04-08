package org.nymph

import net.mamoe.mirai.console.command.*
import org.nymph.DialogueData.groupConfiguration


object SetProhibitedWord : SimpleCommand(
    Dialogue, "SetProhibitedWord", "设定违禁词",
    description = "违禁词设定"
) {
    override val usage: String = "${CommandManager.commandPrefix}设定违禁词 <违禁词>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(answer: String) {
        if (group.botMuteRemaining > 0) return
        
        groupConfiguration.getOrPut(group.id) {
            GroupSet(33, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf())
        }.prohibitedWord.add(answer)

        sendMessage("添加完成")
    }
}