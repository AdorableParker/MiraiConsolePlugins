package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object Blackjack : KotlinPlugin(JvmPluginDescription(
    id = "org.nymph.blackjack",
    name = "Blackjack",
    version = "0.1.0",
) {
    author("parker")
    dependsOn("org.nymph.personal-account","[0.1.0,2.0.0)")
    info("""21点-TB插件子功能模块""")
}) {
    override fun onEnable() {
        logger.info { "$info-已加载" }

        BlackjackData.reload()

        Opening.register()
        SignUp.register()
        Deal.register()
        Shuffle.register()
        Stand.register()

    }

    override fun onDisable() {
        Opening.unregister()
        SignUp.unregister()
        Deal.unregister()
        Shuffle.unregister()
        Stand.unregister()
    }
}