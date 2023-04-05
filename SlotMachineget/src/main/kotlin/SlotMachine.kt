package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregisterAll
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object SlotMachine : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.tiget",
        name = "Slot Machine",
        version = "0.1.0",
    ) {
        author("parker")
        dependsOn("org.nymph.personal-account", "[0.1.0,2.0.0)")
        info("""老虎机-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        AutoBetting.register()
    }
    override fun onDisable() {
        AutoBetting.unregister()
    }
}