package org.nymph


import SQLite
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.version
import net.mamoe.mirai.utils.error
import net.mamoe.mirai.utils.info
import org.nymph.Data.initialization
import org.nymph.Data.version


object AzurLaneLibrary : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.azurlane-library",
        name = "AzurLaneLibrary",
        version = "0.1.1",
    ) {
        author("parker")
        info("""碧蓝航线数据库-TB插件子功能模块""")
    }
) {
    val SQLiteLink = SQLite(resolveDataPath("AssetData.db"))

    override fun onEnable() {
        logger.info { "$info-已加载" }
        Data.reload()
        if (initialization || Data.version < 11){
            val resourceDB = getResourceAsStream("AssetData.db")
            if (resourceDB != null) {
                val file = resolveDataFile("AssetData.db").outputStream()
                file.write(resourceDB.readBytes())
                file.close()
                initialization = false
                Data.version = 11
                logger.info { "$info-初始化完成" }
            } else logger.error { "$info-初始化异常" }
        }
        logger.info { SQLiteLink.open() }
        ShipMap.register()
        Construction.register()
        Roster.register()
        Birthday.register()
        DataVersion.register()
    }

    override fun onDisable() {
        ShipMap.unregister()
        Construction.unregister()
        Roster.unregister()
        Birthday.unregister()
        DataVersion.register()
        SQLiteLink.close()
    }
}