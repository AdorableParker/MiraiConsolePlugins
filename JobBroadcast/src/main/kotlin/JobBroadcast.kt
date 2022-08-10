package org.nymph

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.disable
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.broadcast
import net.mamoe.mirai.utils.info
import org.nymph.JobData.timeAxis
import java.time.LocalDateTime

object JobBroadcast : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.job-broadcast",
        name = "JobBroadcast",
        version = "0.1.0",
    ) {
        author("parker")
        info("""定时任务-TB插件子功能模块""")
    }
) {

    override fun onEnable() {
        logger.info { "$info-已加载" }
        JobData.reload()

        JobQuery.register()

        launch { start() }

    }

    override fun onDisable() {
        JobQuery.unregister()
    }

    private suspend fun start() {
        while (true) {
            val nowTime = LocalDateTime.now()
            val serialNumber = nowTime.dayOfYear * 10000 + nowTime.hour * 100 + nowTime.minute //序列号
            timeAxis.filter { serialNumber >= it.key }.forEach { (key, jobList) ->
                for (jobEvent in jobList) {
                    jobEvent.broadcast()
                    if (jobEvent.intervals >= 0) timeAxis.getOrPut(key + jobEvent.intervals) { mutableListOf() }.add(jobEvent)
                }
                timeAxis.remove(key)
            }
            delay(60000)
        }
    }
}

