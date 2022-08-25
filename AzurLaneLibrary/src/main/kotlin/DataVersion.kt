package org.nymph

import net.mamoe.mirai.console.command.*
import org.nymph.AzurLaneLibrary.SQLiteLink


object DataVersion : SimpleCommand(
    AzurLaneLibrary, "DataVersion", "数据版本",
    description = "数据库数据版本"
) {
    override val usage = "${CommandManager.commandPrefix}数据版本\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        sendMessage(getVersion())
    }

    @Handler
    suspend fun FriendCommandSenderOnMessage.main() {
        sendMessage(getVersion())
    }

    @Handler
    suspend fun GroupTempCommandSenderOnMessage.main() {
        sendMessage(getVersion())
    }

    @Handler
    suspend fun ConsoleCommandSender.main() {
        sendMessage(getVersion())
    }

    private fun getVersion():String{
        val result = SQLiteLink.executeDQLorDCL<LastModifiedTime>("SELECT * FROM LastModifiedTime")
        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> "没有获取到 AzurLaneLibrary 的版本信息"
            else -> "${result.data[0].value}"
        }
    }
}