package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object TemplateTeaching : SimpleCommand(
    Dialogue, "TemplateTeaching", "模板教学",
    description = "AI功能",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.templateTeaching(question: String, answer: String) {
        if (group.botMuteRemaining > 0) return

        val templateIdentifierQ = "\\[]".toRegex()
        val templateIdentifierA = "\\[(.*?)]".toRegex()
        val patternQ = ("^${templateIdentifierQ.replace(question, "(.+)")}$")
        val patternA = templateIdentifierA.replace(answer, "\\$$1")

        DialogueData.QASheet.getOrPut(group.id) { mutableSetOf() }.add(QAPair(patternQ, patternA))
        sendMessage("模板已添加")
    }
}