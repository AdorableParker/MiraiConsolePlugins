package org.nymph

import SQLiteJDBC
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.EventPriority
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.message.data.toMessageChain
import net.mamoe.mirai.utils.info
import org.nymph.DialogueData.PublicInvalidQuestionFeedback
import org.nymph.DialogueData.PublicPenaltyFeedback
import org.nymph.DialogueData.groupConfiguration
import org.nymph.DialogueData.initialization

object Dialogue : KotlinPlugin(JvmPluginDescription(
    id = "org.nymph.dialogue",
    name = "Dialogue",
    version = "0.2.2",
) {
    author("parker")
    info("""聊天问答-TB插件子功能模块""")
}) {

    val SQLiteLink = SQLiteJDBC(resolveDataPath("AI.db"))

    override fun onEnable() {

        logger.info { System.getProperty("os.name") }
        logger.info { System.getProperty("java.runtime.name", "") }

        logger.info { "$info-已加载" }

        DialogueData.reload()
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
        EIDQuery.register()
        Statistics.register()
        TemplateQuery.register()
        ThesaurusQuery.register()
        ThesaurusDelete.register()
        SetInvalidProblemFeedback.register()
        SetPenaltyFeedback.register()
        SetProhibitedWord.register()
        SetTriggerProbability.register()

        this.globalEventChannel().subscribeGroupMessages(priority = EventPriority.LOWEST) {
            atBot {
                if (group.botMuteRemaining > 0) return@atBot
                val groupSet = groupConfiguration.getOrPut(group.id) {
                    GroupSet(33, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf())
                }
                val filterMessageChain = message.filter { it !is At }.toMessageChain()
                if ((1..5).random() <= 2) {
                    val regex = groupSet.prohibitedWord.joinToString("|")
                    if (regex.isNotBlank()) {
                        if (filterMessageChain.content.trim()
                                .contains(regex.toRegex()) && group.botPermission > this.sender.permission
                        ) {
                            this.sender.mute((300..900).random())
                            group.sendMessage(
                                groupSet.penaltyFeedback.let {
                                    if (it.isEmpty()) PublicPenaltyFeedback else it
                                }.random()
                            )
                            return@atBot
                        }
                    }
                }
                group.sendMessage(AI.dialogue(group.id, filterMessageChain.content.trim())
                    ?: groupSet.invalidProblemFeedback.let {
                        if (it.isEmpty()) PublicInvalidQuestionFeedback.random() else it.random()
                    })
            }
            atBot().not().invoke {
                if (group.botMuteRemaining > 0 && (1..100).random() <= groupConfiguration.getOrPut(group.id) {
                        GroupSet(33, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf())
                    }.triggerProbability) return@invoke
                AI.dialogue(group.id, message.content.trim())?.let { answer ->
                    group.sendMessage(answer)
                }
            }
        }
    }

    override fun onDisable() {
        TemplateTeaching.unregister()
        Teaching.unregister()
        EIDQuery.unregister()
        Statistics.unregister()
        TemplateQuery.unregister()
        ThesaurusQuery.unregister()
        ThesaurusDelete.unregister()
        SetInvalidProblemFeedback.unregister()
        SetPenaltyFeedback.unregister()
        SetProhibitedWord.unregister()
        SetTriggerProbability.unregister()
        SQLiteLink.close()
    }
}