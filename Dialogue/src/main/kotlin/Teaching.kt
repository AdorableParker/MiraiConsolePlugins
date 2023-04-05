package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.message.data.sendTo
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import org.nymph.Dialogue.SQLiteLink
import org.nymph.Dialogue.getResourceAsStream
import org.nymph.Dialogue.logger

object Teaching : SimpleCommand(
    Dialogue, "teaching", "教学", description = "问答教学"
) {
    override val usage: String = "${CommandManager.commandPrefix}教学 <问题> <答案>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(question: String, answer: String) {

        if (group.botMuteRemaining > 0) return
        val result = SQLiteLink.safeExecuteDQLorDCL<CorpusData>(
            "SELECT * FROM Corpus WHERE question = ? AND answer= ? AND fromGroup= ?;",
            question,
            answer,
            group.id
        )
        result.error?.let { error ->
            sendMessage(error)
            return
        }
        if (result.data.isNotEmpty()) {
            sendMessage("问题:$question\n回答:$answer\n该条目已存在，条目ID:${result.data[0].id}")
            return
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
