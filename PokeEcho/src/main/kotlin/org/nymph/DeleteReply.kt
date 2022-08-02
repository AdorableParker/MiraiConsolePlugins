package org.nymph.org.nymph

import net.mamoe.mirai.console.command.FriendCommandSenderOnMessage
import net.mamoe.mirai.console.command.GroupTempCommandSender
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.Data.echoDataSet
import org.nymph.PokeEcho

object DeleteReply : SimpleCommand(
    PokeEcho, "DeleteReply", "删除被戳回复",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(echo: String) {
        if (group.botMuteRemaining > 0) return
        if(echoDataSet.remove(echo))
            sendMessage("删除成功")
        else
            sendMessage("目标不存在")
    }

    @Handler
    suspend fun FriendCommandSenderOnMessage.main(echo: String) {
        if (echoDataSet.remove(echo))
            sendMessage("删除成功")
        else
            sendMessage("目标不存在")
    }

    @Handler
    suspend fun GroupTempCommandSender.main(echo: String) {
        if (echoDataSet.remove(echo))
            sendMessage("删除成功")
        else
            sendMessage("目标不存在")
    }
}
