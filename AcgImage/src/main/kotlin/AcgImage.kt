package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object AcgImage : KotlinPlugin(JvmPluginDescription.loadFromResource()) {

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

