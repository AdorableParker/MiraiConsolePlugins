package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object ThesaurusQuery : SimpleCommand(
    Dialogue, "Query", "查询",
    description = "问答查询"
) {
    override val usage: String = "${CommandManager.commandPrefix}查询 <关键词>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(key: String) {
        if (group.botMuteRemaining > 0) return

        val result =
            Dialogue.SQLiteLink.executeDQLorDCL<CorpusData> { "SELECT * FROM Corpus WHERE answer GLOB '*$key*' OR question GLOB '*$key*';" }
        result.error?.let { error ->
            sendMessage(error)
            return
        }
        val data = result.data
        when {
            data.isEmpty() -> "问答包含关键词${key}的条目不存在"
            data.size >= 30 -> data.joinToString(
                "\n",
                "问答包含关键词${key}的条目过多(超过三十条)，仅显示ID\n"
            ) { "ID:${it.id}" }

            data.size >= 10 -> data.filter { it.fromGroup == group.id }.union(
                data.filter { it.fromGroup == 0L }
            ).take(10)
                .joinToString("\n", "问答包含关键词${key}的条目过多(超过十条)，仅提供前十条，本群关键词优先显示\n") {
                    if (group.id == it.fromGroup) "问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:完全控制\n"
                    else "问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:只读权限\n"
                }

            else -> data.joinToString("\n", "条目清单:\n") {
                when (it.fromGroup) {
                    group.id -> "问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:完全控制\n"
                    0L -> "问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:只读权限\n"
                    else -> "问题:隐藏\t回答:隐藏\n条目ID:${it.id}\n控制权限:不可操作\n"
                }
            }
        }.let { sendMessage(it) }
    }
}