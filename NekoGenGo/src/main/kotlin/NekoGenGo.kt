package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object NekoGenGo : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.neko-gengo",
        name = "NekoGenGo",
        version = "0.1.1",
    ) {
        author("parker")
        info("""喵语翻译-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        Hanashi.register()
        Honyaku.register()
    }

    override fun onDisable() {
        Hanashi.unregister()
        Honyaku.unregister()
    }
}


