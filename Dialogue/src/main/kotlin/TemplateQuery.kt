package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object TemplateQuery : SimpleCommand(
    Dialogue, "templateQuery", "模板查询",
    description = "模板问答查询"
) {
    override val usage: String = "${CommandManager.commandPrefix}模板查询 \t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val qaSheet = DialogueData.groupConfiguration.getOrPut(group.id) {
            GroupSet(33, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf())
        }.qaSheet
        if (qaSheet.isEmpty()) {
            sendMessage("本群尚无问答模板")
            return
        }
        qaSheet.joinToString {
            "问:${
                it.question.replace("(.+)", "[]").trim('^', '$')
            }\t答:${
                "\\$(\\d+)".toRegex().replace(it.answer, "[$1]")
            }\n"
        }
    }
}