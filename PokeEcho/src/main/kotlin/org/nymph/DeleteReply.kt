package org.nymph.org.nymph

import net.mamoe.mirai.console.command.*
import org.nymph.Data.echoDataSet
import org.nymph.PokeEcho

object DeleteReply : SimpleCommand(
    PokeEcho, "DeleteReply", "删除被戳回复",
    description = "删除被戳回复"
) {
    override val usage: String = "${CommandManager.commandPrefix}删除被戳回复 [回复内容]\t#$description"
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
