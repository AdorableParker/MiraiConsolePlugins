package org.nymph

import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.utils.info
import net.mamoe.mirai.utils.warning
import org.nymph.Dynamic.getDynamic
import org.nymph.DynamicPushData.subscriptionList
import org.nymph.DynamicPushData.timeStampOfDynamic
import org.nymph.DynamicPushSetting.BotID
import java.time.LocalDateTime

object DynamicPush : KotlinPlugin(JvmPluginDescription.loadFromResource()) {
    override fun onEnable() {
        logger.info { "$info-已加载" }

        DynamicPushSetting.reload()
        DynamicPushData.reload()

        SendDynamic.register()
        SubscriptionDynamics.register()
        Unsubscribe.register()
        SetBotID.register()

        PushEvent(
            intervals = 3,
            name = "动态推送",
            synopsis = "获取B站动态更新信息"
        ).addJob(LocalDateTime.now().dayOfYear * 10000 + LocalDateTime.now().hour * 100 + LocalDateTime.now().minute)

        GlobalEventChannel.subscribeAlways<PushEvent> {
            val bot = runCatching {
                Bot.getInstance(BotID)
            }.getOrElse {
                logger.warning { "获取Bot对象异常,请检查BotID" }
                return@subscribeAlways
            }

            for ((uid, _) in timeStampOfDynamic) {
                val dynamicInfo = getDynamic(uid, 0, flag = true)
                if (dynamicInfo.timestamp == 0L) continue
                subscriptionList[uid]?.forEach { group ->
                    val g = bot.getGroup(group)
                    if (g != null && g.botMuteRemaining <= 0) g.sendMessage("${dynamicInfo.name}:\n${dynamicInfo.text}")
                }
            }
        }
    }

    override fun onDisable() {
        SendDynamic.unregister()
        SubscriptionDynamics.unregister()
        Unsubscribe.unregister()
        SetBotID.unregister()
    }
}