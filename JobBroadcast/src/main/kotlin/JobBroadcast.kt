package org.nymph

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.broadcast
import net.mamoe.mirai.utils.info
import java.time.LocalDateTime

object JobBroadcast : KotlinPlugin(
    JvmPluginDescription(
        id = "org.nymph.job-broadcast",
        name = "JobBroadcast",
        version = "0.1.2",
    ) {
        author("parker")
        info("""定时任务-TB插件子功能模块""")
    }
) {

    val timeAxis: MutableMap<Int, MutableList<JobEvent>> = mutableMapOf()

    override fun onEnable() {
        logger.info { "$info-已加载" }


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
                    if (jobEvent.intervals >= 0) {
                        val t = key + jobEvent.intervals
                        var m = t % 100
                        var h = t / 100 % 100
                        var d = t / 10000

                        if (m >= 60) {
                            m %= 60
                            h++
                        }
                        if (h >= 24) {
                            h %= 24
                            d++
                        }
                        if (d >= 366) d %= 366
                        timeAxis.getOrPut(d * 10000 + h * 100 + m) { mutableListOf() }.add(jobEvent)
                    }
                }
                timeAxis.remove(key)
            }
            delay(60000)
        }
    }
}

