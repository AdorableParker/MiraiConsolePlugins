package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info


object CloudRaiseCat : KotlinPlugin(JvmPluginDescription(
    id = "org.nymph.cloud-raise-cat",
    name = "CloudRaiseCat",
    version = "0.1.1",
) {
    author("parker")
    dependsOn("org.nymph.personal-account", "[0.1.0,2.0.0)")
    info("""云养猫-TB插件子功能模块""")
}) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        CloudCatData.reload()

        BuyCat.register()
        BuyCannedCat.register()
        FeedTheCat.register()
        CatWork.register()
        RuaCat.register()
        CatPK.register()
        CatState.register()
    }

    override fun onDisable() {
        BuyCat.unregister()
        BuyCannedCat.unregister()
        FeedTheCat.unregister()
        CatWork.unregister()
        RuaCat.unregister()
        CatPK.unregister()
        CatState.unregister()
    }
}


