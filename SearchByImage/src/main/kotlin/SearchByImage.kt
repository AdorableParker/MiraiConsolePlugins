import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object SearchByImage : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.search-by-image",
        name = "SearchByImage",
        version = "0.1.1",
    ) {
        author("parker")
        info("""搜图-TB插件子功能模块""")
    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        Data.reload()

        SauceNAO.register()
        SetApiKey.register()
    }

    override fun onDisable() {
        SauceNAO.unregister()
        SetApiKey.unregister()
    }
}