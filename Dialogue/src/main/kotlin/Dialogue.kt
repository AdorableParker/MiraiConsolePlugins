package org.nymph

import SQLite
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.EventPriority
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.info
import org.nymph.DialogueData.initialization
import org.nymph.DialogueData.invalidProblemFeedback
import org.nymph.DialogueData.penaltyFeedback
import org.nymph.DialogueData.prohibitedWord
import org.nymph.DialogueData.triggerProbability

object Dialogue : KotlinPlugin(JvmPluginDescription(
    id = "org.nymph.dialogue",
    name = "Dialogue",
    version = "0.1.0",
) {
    author("parker")
    info("""聊天问答-TB插件子功能模块""")
}) {

    val SQLiteLink = SQLite(resolveDataPath("AI.db"))

    override fun onEnable() {
        logger.info { "$info-已加载" }
        DialogueData.reload()
//        val str = ""
//        logger.info { ToAnalysis.parse(str).toString() }
//        KeyWordComputer<ToAnalysis>()
        if (initialization) {
            SQLiteLink.executeDMLorDDL(
                """
                CREATE TABLE "Corpus" (
                "id"	INTEGER NOT NULL UNIQUE,
                "answer"	TEXT NOT NULL,
                "question"	TEXT NOT NULL,
                "fromGroup"	INTEGER NOT NULL,
                PRIMARY KEY("id" AUTOINCREMENT)
                );
                """.trimIndent()
            ).let(logger::info)
            initialization = false
        }
        TemplateTeaching.register()
        Teaching.register()
        SetInvalidProblemFeedback.register()
        SetPenaltyFeedback.register()
        SetProhibitedWord.register()
        SetTriggerProbability.register()

        this.globalEventChannel().subscribeGroupMessages(priority = EventPriority.LOWEST) {
            atBot {
                if (group.botMuteRemaining > 0) return@atBot
                val filterMessageList: List<Message> = message.filter { it !is At }
                val filterMessageChain: MessageChain = filterMessageList.toMessageChain()
                if ((1..5).random() <= 2) {
                    if (filterMessageChain.content.trim().contains(
                            prohibitedWord.joinToString("|").toRegex()
                        ) && group.botPermission > this.sender.permission
                    ) {
                        this.sender.mute((300..900).random())
                        group.sendMessage(penaltyFeedback.random())
                        return@atBot
                    }
                }
                val answer = AI.dialogue(group.id, filterMessageChain.content.trim()) ?: invalidProblemFeedback.random()
                group.sendMessage(answer)
            }
            atBot().not().invoke {
                if (group.botMuteRemaining > 0 && (1..100).random() >= triggerProbability.getOrPut(group.id) { 33 }) return@invoke
                AI.dialogue(group.id, message.content.trim())?.let { answer ->
                    group.sendMessage(answer)
                }
            }
        }
    }

    override fun onDisable() {
        TemplateTeaching.unregister()
        Teaching.unregister()
        SetInvalidProblemFeedback.unregister()
        SetPenaltyFeedback.unregister()
        SetProhibitedWord.unregister()
        SetTriggerProbability.unregister()
        SQLiteLink.close()
    }
}