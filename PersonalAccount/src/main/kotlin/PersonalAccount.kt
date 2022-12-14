package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object PersonalAccount : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.personal-account",
        name = "PersonalAccount",
        version = "0.1.1",
    ) {
        author("parker")
        info("""个人账户-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        Account.reload()

        MyInfo.register()
        GoldCoinRank.register()
        CubeCoinRank.register()
    }

    override fun onDisable() {
        MyInfo.unregister()
        GoldCoinRank.unregister()
        CubeCoinRank.unregister()
    }
}