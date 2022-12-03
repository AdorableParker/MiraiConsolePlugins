package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.Event
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.event.events.MemberLeaveEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.utils.info
import org.nymph.SeIfHelpManagementData.enableReportingList

object SelfHelpManagement : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.self-help-management",
        name = "SelfHelpManagement",
        version = "0.1.2",
    ) {
        author("parker")
        info("""自助群管-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        GaG.register()
        TitleToApply.register()
        SetEnableReporting.register()

        this.globalEventChannel().subscribeAlways<Event> {
            when (this) {
                is MemberJoinEvent.Invite -> {
                    if (groupId in enableReportingList && group.botMuteRemaining <= 0)
                        group.sendMessage("${invitor.nameCardOrNick}邀请${member.nameCardOrNick}大佬加入群聊")
                }          // 邀请加入播报
                is MemberJoinEvent.Active -> {
                    if (groupId in enableReportingList && group.botMuteRemaining <= 0)
                        group.sendMessage("欢迎大佬${member.nameCardOrNick}加入群聊")
                }          // 主动加入播报
                is MemberJoinEvent.Retrieve -> {
                    if (groupId in enableReportingList && group.botMuteRemaining <= 0)
                        group.sendMessage("欢迎群主${member.nameCardOrNick}回归")
                }        // 恢复加入播报
                is MemberLeaveEvent.Kick -> {
                    if (groupId in enableReportingList && group.botMuteRemaining <= 0)
                        group.sendMessage("哇啊,${member.nameCardOrNick}(${member.id})被${(operator ?: bot).nameCardOrNick}鲨掉惹")
                }           // 成员移出播报
                is MemberLeaveEvent.Quit -> {
                    if (groupId in enableReportingList && group.botMuteRemaining <= 0)
                        group.sendMessage("哇啊,${member.nameCardOrNick}(${member.id})自己跑掉惹")
                }           // 成员退群播报
            }
        }
    }

    override fun onDisable() {
        GaG.unregister()
        TitleToApply.unregister()
        SetEnableReporting.unregister()
    }
}

