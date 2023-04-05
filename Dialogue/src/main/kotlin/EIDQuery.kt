package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object EIDQuery : SimpleCommand(
    Dialogue, "EIDQuery", "EID查询",
    description = "根据条目ID查询"
) {
    override val usage: String = "${CommandManager.commandPrefix}EID查询 [关键词]\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(eid: Int) {
        if (group.botMuteRemaining > 0) return

//        val result = Dialogue.SQLiteLink.executeDQLorDCL<CorpusData> { "SELECT * FROM Corpus WHERE id = $eid;" }
        val result = Dialogue.SQLiteLink.safeExecuteDQLorDCL<CorpusData>("SELECT * FROM Corpus WHERE id = ?;", eid)

        result.error?.let {
            sendMessage(it)
            return
        }
        result.data.let {
            if (it.isEmpty()) "条目${eid}不存在"
            else when (it[0].fromGroup) {
                group.id -> "问题:${it[0].question}\n回答:${it[0].answer}\n条目ID:${it[0].id}\n控制权限:完全控制"
                0L -> "问题:${it[0].question}\n回答:${it[0].answer}\n条目ID:${it[0].id}\n控制权限:只读权限"
                else -> "问题:隐藏\t回答:隐藏\n条目ID:${it[0].id}\n控制权限:不可操作"
            }
        }.let { sendMessage(it) }
    }
}