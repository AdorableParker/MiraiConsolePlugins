package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object Moegoe : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.moegoe",
        name = "Moegoe",
        version = "0.1.0",
    ) {
        author("parker")
        info("萌音-TB插件子功能模块")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        T2JS.register()
        T2CS.register()
    }


    override fun onDisable() {
        T2JS.unregister()
        T2CS.unregister()
    }
}

