package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object AcgImage : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.acg-image",
        name = "AcgImage",
        version = "0.1.0",
    ) {
        author("parker")
        info("""随机图片-TB插件子功能模块""")
        dependsOn("org.nymph.personal-account:[0.1.0,2.0.0)")
    }
) {

    override fun onEnable() {
        logger.info { "$info-已加载" }
        DailyImage.register()
        SetProxy.register()
    }

    override fun onDisable() {
        DailyImage.unregister()
        SetProxy.unregister()
    }
}

