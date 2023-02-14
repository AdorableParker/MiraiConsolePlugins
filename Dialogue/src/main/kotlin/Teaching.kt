package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.message.data.sendTo
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import org.nymph.Dialogue.SQLiteLink
import org.nymph.Dialogue.getResourceAsStream
import org.nymph.Dialogue.logger
import sqliteJDBC.SQLResult

object Teaching : SimpleCommand(
    Dialogue, "teaching", "教学",
    description = "问答教学"
) {
    override val usage: String = "${CommandManager.commandPrefix}教学 <问题> <答案>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(question: String, answer: String) {
        if (group.botMuteRemaining > 0) return
        val result = SQLResult("Testing", listOf<CorpusData>())
        SQLiteLink.executeDQLorDCL<CorpusData> { "SELECT * FROM Corpus WHERE question = '$question' AND answer='$answer' AND fromGroup=${group.id};" }
        when {
            result.error != null -> {
                sendMessage(result.error)
                return
            }

            result.data.isNotEmpty() -> {
                sendMessage("问题:$question\n回答:$answer\n该条目已存在，条目ID:${result.data[0].id}")
                return
            }
        }

        SQLiteLink.executeDMLorDDL {
            "INSERT INTO Corpus (answer,question,fromGroup) VALUES ('$answer', '$question', ${group.id} );"
        }.let(logger::info)
        if ((0..4).random() == 0) {
            val audio = getResourceAsStream("雷-原来如此.amr")?.toExternalResource()
            audio.use { resource -> resource?.let { group.uploadAudio(it).sendTo(group) } }
        }
        sendMessage("问题:$question\n回答:$answer\n条目已添加")
    }
}
/*
    @SubCommand("查询")
    suspend fun MemberCommandSenderOnMessage.query(key: String) {
        if (group.botMuteRemaining > 0) return
        if (group.id !in ActiveGroupList.user) {
            sendMessage("本群授权已到期,请续费后使用")
            return
        }

        val dbObject = SQLiteJDBC(PluginMain.resolveDataPath("AI.db"))
        val entryList = dbObject.executeQuerySQL(
            "SELECT * FROM Corpus WHERE answer GLOB '*$key*' OR question GLOB '*$key*' OR keys GLOB '*$key*';",
            "AI查询\nFile:AI.kt\tLine:87"
        ).run {
            List(size) { AICorpus(this[it]) }
        }
        dbObject.closeDB()
        val r = when {
            entryList.isEmpty() -> "问答包含关键词${key}的条目不存在"
            entryList.size >= 30 -> "问答包含关键词${key}的条目过多(超过三十条)，请提供更加详细的关键词"
            entryList.size >= 10 -> {
                val report = mutableListOf("问答包含关键词${key}的条目过多(超过十条)，仅提供前十条，本群关键词优先显示")
                entryList.forEach {
                    if (report.size <= 10)
                        if (it.fromGroup == group.id)
                            report.add("问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:完全控制\n")
                        else if (it.fromGroup == 0L)
                            report.add("问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:只读权限\n")
                }
                report.joinToString("\n")
            }

            else -> {
                val report = mutableListOf("条目清单:")
                entryList.forEach {
                    when (it.fromGroup) {
                        group.id -> report.add("问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:完全控制\n")
                        0L -> report.add("问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:只读权限\n")
                        else -> report.add("问题:隐藏\t回答:隐藏\n条目ID:${it.id}\n控制权限:不可操作\n")
                    }
                }
                report.joinToString("\n")
            }
        }
        sendMessage(r)
    }

    @SubCommand("模板查询")
    suspend fun MemberCommandSenderOnMessage.templateQuery() {
        if (group.botMuteRemaining > 0) return
        if (group.id !in ActiveGroupList.user) {
            sendMessage("本群授权已到期,请续费后使用")
            return
        }
        val qaSet = AiTemplate.QASheet[group.id]
        if (qaSet.isNullOrEmpty()) {
            sendMessage("本群尚无问答模板")
            return
        }
        val r = StringBuffer()
        qaSet.forEach {
            val outQ = it.question.replace("(.+)", "[]").trim('^', '$')
            val outA = "\\$(\\d+)".toRegex().replace(it.answer, "[$1]")
            r.append("问:${outQ}\t答:${outA}\n")
        }
        sendMessage(r.toString())
    }

    @SubCommand("统计")
    suspend fun MemberCommandSenderOnMessage.statistics() {
        if (group.botMuteRemaining > 0) return
        if (group.id !in ActiveGroupList.user) {
            sendMessage("本群授权已到期,请续费后使用")
            return
        }

        val dbObject = SQLiteJDBC(PluginMain.resolveDataPath("AI.db"))
        val entryList = dbObject.executeQuerySQL("SELECT * FROM Corpus;", "AI统计\nFile:AI.kt\tLine:132").run {
            List(size) { AICorpus(this[it]) }
        }
        dbObject.closeDB()
        val cSpecial = entryList.count { it.fromGroup == group.id }
        val cAvailable = entryList.count { it.fromGroup == 0L } + cSpecial
        sendMessage(
            if (entryList.isEmpty()) "统计查询失败" else "目前数据库教学数据共计${entryList.size}条\n本群可读教学数据${cAvailable}条\n其中本群专属教学数据${cSpecial}条\n占本群可读的${
                "%.2f".format(
                    cSpecial.toDouble() / cAvailable * 100
                )
            }%,占数据库总量的${"%.2f".format(cSpecial.toDouble() / entryList.size * 100)}%"
        )
    }

    @SubCommand("EID查询")
    suspend fun MemberCommandSenderOnMessage.eIDMain(EID: Int) {
        if (group.botMuteRemaining > 0) return
        if (group.id !in ActiveGroupList.user) {
            sendMessage("本群授权已到期,请续费后使用")
            return
        }

        val dbObject = SQLiteJDBC(PluginMain.resolveDataPath("AI.db"))
        val entryList = dbObject.select("Corpus", Triple("id", "=", "$EID"), "AI_EID查询\nFile:AI.kt\tLine:156").run {
            List(size) { AICorpus(this[it]) }
        }
        dbObject.closeDB()
        sendMessage(when {
            entryList.isEmpty() -> "条目${EID}不存在"
            else -> {
                val report = mutableListOf("条目清单:")
                entryList.forEach {
                    when (it.fromGroup) {
                        group.id -> report.add("问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:完全控制")
                        0L -> report.add("问题:${it.question}\n回答:${it.answer}\n条目ID:${it.id}\n控制权限:只读权限")
                        else -> report.add("问题:隐藏\t回答:隐藏\n条目ID:${it.id}\n控制权限:不可操作")
                    }
                }
                report.joinToString("\n")
            }
        })
    }

    @SubCommand("删除")
    suspend fun MemberCommandSenderOnMessage.delete(EID: Int) {
        if (group.botMuteRemaining > 0) return
        if (group.id !in ActiveGroupList.user) {
            sendMessage("本群授权已到期,请续费后使用")
            return
        }

        val dbObject = SQLiteJDBC(PluginMain.resolveDataPath("AI.db"))
        val entry = dbObject.selectOne("Corpus", Triple("id", "=", "$EID"), "AI删除\nFile:AI.kt\tLine:185").run {
            if (isEmpty()) {
                dbObject.closeDB()
                sendMessage("没有该条目")
                return
            }
            AICorpus(this)
        }
        if (entry.fromGroup == group.id || user.id == MySetting.AdminID) {
            dbObject.delete("Corpus", Pair("id", "$EID"), "AI删除\nFile:AI.kt\tLine:190")
            sendMessage("问题:${entry.question}\n回答:${entry.answer}\n条目ID:${entry.id}\n条目已删除")
            return dbObject.closeDB()
        } else sendMessage("权限不足")
        dbObject.closeDB()
    }


}
*/