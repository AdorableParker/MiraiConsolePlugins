package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.JobBroadcast.timeAxis


object JobQuery : SimpleCommand(
    JobBroadcast, "JobQuery", "任务查询"
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        val l = StringBuilder("序列号 | 任务数\n--------|---------")

        timeAxis.forEach { (serialNumber, jobList) ->
            l.append("\n$serialNumber|${jobList.size}")
        }
        sendMessage(l.toString())
    }


    @Handler
    suspend fun MemberCommandSenderOnMessage.main(deadline: Int) {
        sendMessage(timeAxis[deadline]?.joinToString("\n", "序列${deadline}任务有:\n") { "${it.name}:${it.synopsis}" }
            ?: "序列${deadline}无任务")
    }
}
