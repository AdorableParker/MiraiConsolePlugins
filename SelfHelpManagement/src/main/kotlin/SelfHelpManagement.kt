package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object SelfHelpManagement : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.self-help-management",
        name = "SelfHelpManagement",
        version = "0.1.1",
    ) {
        author("parker")
        info("""自助群管-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        GaG.register()
        TitleToApply.register()
    }

    override fun onDisable() {
        GaG.unregister()
        TitleToApply.unregister()
    }
}

