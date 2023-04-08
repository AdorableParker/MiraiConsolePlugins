package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.Dialogue.logger


object ThesaurusDelete : SimpleCommand(
    Dialogue, "ThesaurusDelete", "删除",
    description = "根据条目ID删除"
) {
    override val usage: String = "${CommandManager.commandPrefix}删除 <关键词>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(eid: Int) {
        if (group.botMuteRemaining > 0) return

        Dialogue.SQLiteLink.executeDMLorDDL("DELETE FROM Corpus WHERE id = $eid AND fromGroup = ${group.id};").let(logger::info)
        sendMessage("已执行")
    }
}