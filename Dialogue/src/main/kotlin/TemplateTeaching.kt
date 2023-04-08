package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object TemplateTeaching : SimpleCommand(
    Dialogue, "TemplateTeaching", "模板教学",
    description = "问答模板教学"
) {
    override val usage: String = "${CommandManager.commandPrefix}模板教学 <模板问题> <模板答案>\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.templateTeaching(question: String, answer: String) {
        if (group.botMuteRemaining > 0) return

        val templateIdentifierQ = "\\[]".toRegex()
        val templateIdentifierA = "\\[(.*?)]".toRegex()
        val patternQ = ("^${templateIdentifierQ.replace(question, "(.+)")}$")
        val patternA = templateIdentifierA.replace(answer, "\\$$1")

        DialogueData.groupConfiguration.getOrPut(group.id) {
            GroupSet(
                33, mutableSetOf(), mutableSetOf(),
                mutableSetOf(), mutableSetOf()
            )
        }.qaSheet.add(QAPair(patternQ, patternA))
        sendMessage("模板已添加")
    }
}