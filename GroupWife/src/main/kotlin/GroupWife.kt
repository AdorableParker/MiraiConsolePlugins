package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object GroupWife : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.group-wife",
        name = "GroupWife",
        version = "0.1.0",
    ) {
        author("parker")
        info("""群老婆-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        WifeToday.register()
    }

    override fun onDisable() {
        WifeToday.unregister()
    }
}