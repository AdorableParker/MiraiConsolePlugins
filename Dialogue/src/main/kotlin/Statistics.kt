package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Statistics : SimpleCommand(
    Dialogue, "Statistics", "统计",
    description = "统计问答数据"
) {
    override val usage: String = "${CommandManager.commandPrefix}统计 [关键词]\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(key: String) {
        if (group.botMuteRemaining > 0) return

        val result =
            Dialogue.SQLiteLink.executeDQLorDCL<CorpusData> { "SELECT * FROM Corpus WHERE answer GLOB '*$key*' OR question GLOB '*$key*';" }
        result.error?.let { error ->
            sendMessage(error)
            return
        }

        (if (result.data.isEmpty()) "统计查询失败"
        else {
            val cSpecial = result.data.count { it.fromGroup == group.id }
            val cAvailable = result.data.count { it.fromGroup == 0L } + cSpecial
            "目前数据库含关键词[$key]的教学数据共计${result.data.size}条\n其中本群可读教学数据${cAvailable}条\n本群专属教学数据${cSpecial}条\n占本群可读的${
                "%.2f".format(
                    cSpecial.toDouble() / cAvailable * 100
                )
            }%,占数据库总量的${"%.2f".format(cSpecial.toDouble() / result.data.size * 100)}%"
        }).let { sendMessage(it) }
    }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val result = Dialogue.SQLiteLink.executeDQLorDCL<CorpusData> { "SELECT * FROM Corpus;" }
        result.error?.let { error ->
            sendMessage(error)
            return
        }

        (if (result.data.isEmpty()) "统计查询失败"
        else {
            val cSpecial = result.data.count { it.fromGroup == group.id }
            val cAvailable = result.data.count { it.fromGroup == 0L } + cSpecial
            "目前数据库教学数据共计${result.data.size}条\n本群可读教学数据${cAvailable}条\n其中本群专属教学数据${cSpecial}条\n占本群可读的${
                "%.2f".format(
                    cSpecial.toDouble() / cAvailable * 100
                )
            }%,占数据库总量的${"%.2f".format(cSpecial.toDouble() / result.data.size * 100)}%"
        }).let { sendMessage(it) }
    }
}