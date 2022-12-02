package org.nymph

import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.utils.info
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

        PushEvent(
            intervals = 3,
            name = "动态推送",
            synopsis = "获取B站动态更新信息"
        ).addJob(LocalDateTime.now().dayOfYear * 10000 + LocalDateTime.now().hour * 100 + LocalDateTime.now().minute)

        GlobalEventChannel.subscribeAlways<PushEvent> {
            val bot = Bot.getInstance(BotID)
            for ((uid, timestamp) in timeStampOfDynamic) {
                val dynamicInfo = SendDynamic.getDynamic(uid, 0, flag = true)
                if (dynamicInfo.timestamp - timestamp <= 0L) continue
                subscriptionList[uid]?.forEach { group ->
                    val g = bot.getGroup(group)
                    if (g != null && g.botMuteRemaining <= 0) g.sendMessage("${dynamicInfo.name}:\n${dynamicInfo.text}")
                }
            }
        }
    }

    override fun onDisable() {
        SendDynamic.unregister()
    }
}