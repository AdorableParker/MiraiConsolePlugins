package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.data.PluginData
import net.mamoe.mirai.console.data.PluginDataHolder
import net.mamoe.mirai.console.data.PluginDataStorage
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.name
import net.mamoe.mirai.event.events.NudgeEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.utils.warning
import org.nymph.Data.echoDataSet
import org.nymph.PokeEcho.reload
import org.nymph.org.nymph.DeleteReply

object PokeEcho : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.poke-echo",
        name = "PokeEcho",
        version = "0.1.0",
    ) {
        author("parker")
        info("""戳一戳回复-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }

        Data.reload()

        AddReply.register()
        DeleteReply.register()

        this.globalEventChannel().subscribeAlways<NudgeEvent>{
            nudge()
        }
    }

    override fun onDisable() {
        AddReply.unregister()
        DeleteReply.unregister()
    }

    private suspend fun NudgeEvent.nudge() {
        if (target == bot && from != bot) {
            runCatching {
                if ((1..5).random() <= 4) {
                    subject.sendMessage(echoDataSet.random())
                } else {
                    from.nudge().sendTo(subject)
                    subject.sendMessage("戳回去")
                }
            }.onFailure {
                logger.warning { "[$name]发送消息失败:${it.message}" }
            }
        }
    }
}