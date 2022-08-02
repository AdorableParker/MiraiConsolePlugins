package org.nymph

import net.mamoe.mirai.console.command.FriendCommandSenderOnMessage
import net.mamoe.mirai.console.command.GroupTempCommandSender
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.Data.echoDataSet


object AddReply : SimpleCommand(
    PokeEcho, "AddReply", "添加被戳回复",
) {

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