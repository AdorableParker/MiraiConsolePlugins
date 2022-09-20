package org.nymph

import net.mamoe.mirai.console.command.*
import org.nymph.Data.echoDataSet


object AddReply : SimpleCommand(
    PokeEcho, "AddReply", "添加被戳回复",
    description = "添加被戳回复"
) {
    override val usage: String = "${CommandManager.commandPrefix}添加被戳回复 [回复内容]\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(echo:String) {
        if (group.botMuteRemaining > 0) return
        echoDataSet.add(echo)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun FriendCommandSenderOnMessage.main(echo: String) {
        echoDataSet.add(echo)
        sendMessage("添加成功")
    }

    @Handler
    suspend fun GroupTempCommandSender.main(echo: String) {
        echoDataSet.add(echo)
        sendMessage("添加成功")
    }

}