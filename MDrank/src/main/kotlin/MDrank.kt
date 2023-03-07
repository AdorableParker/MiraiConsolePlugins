package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object MDrank : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.md-rank",
        name = "MDrank",
        version = "0.1.1",
    ) {
        author("parker")
        info("""MDrank-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }

        MDAccountData.reload()

        Binding.register()
        MDAccountInfo.register()
    }


    override fun onDisable() {
        Binding.unregister()
        MDAccountInfo.unregister()
    }
}