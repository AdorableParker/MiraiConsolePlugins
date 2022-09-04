package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.DialogueData.triggerProbability

object SetTriggerProbability : SimpleCommand(
    Dialogue, "SetTriggerProbability", "设定聊天概率",
) {

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(probability: Int) {
        if (group.botMuteRemaining > 0) return

        if (probability !in 0..100){
            sendMessage("概率范围应为[0,100]")
            return
        }

        triggerProbability.getOrPut(group.id){ probability }
        sendMessage("设定完成")
    }

}