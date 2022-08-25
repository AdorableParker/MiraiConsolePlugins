package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.AzurLaneLibrary.SQLiteLink

object Construction : SimpleCommand(
    AzurLaneLibrary, "Construction", "建造时间", description = "碧蓝航线建造时间查询"
) {
    override val usage: String = "${CommandManager.commandPrefix}建造时间 [时间|船名]"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(uncheckedIndex: String) {
        if (group.botMuteRemaining > 0) return

        val pretreatmentIndex = uncheckedIndex.replace('：', ':').toCharArray()
        pretreatmentIndex.forEachIndexed { index, char ->
            if (char.isLowerCase()) pretreatmentIndex[index] = char.uppercaseChar()
        }
        val treatedIndex = String(pretreatmentIndex)
        val index = Regex("\\d:\\d\\d").find(treatedIndex)?.value
        index?.let { sendMessage(timeToName(index)) } ?: sendMessage(nameToTime(treatedIndex))
    }

    private fun timeToName(time: String): String {

        val result = SQLiteLink.executeDQLorDCL<ConstructTimeData> {
            "SELECT * FROM ConstructTime WHERE time GLOB '$time*';"
        }
        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> "没有或尚未收录建造时间为 $time 的可建造舰船"
            else -> result.data.sortedWith(compareBy({ it.limitedTime },
                { it.originalName.length },
                { it.alias.length },
                { it.originalName },
                { it.alias })).joinToString("", "建造时间为 $time 的舰船有:") {
                "船名：${it.originalName}[${it.alias}]-\t${
                    when (it.limitedTime) {
                        1 -> "限时"
                        2 -> "建造绝版"
                        else -> "常驻"
                    }
                }\n"
            }
        }
    }

    private fun nameToTime(name: String): String {
        val result = SQLiteLink.executeDQLorDCL<ConstructTimeData> {
            "SELECT * FROM ConstructTime WHERE originalName GLOB '*$name*' OR alias GLOB '*$name*';"
        }

        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> "没有或尚未收录名字包含有 $name 的可建造舰船"
            else -> result.data.sortedWith(compareBy({ it.limitedTime },
                { it.time.split(":")[0] },
                { it.time.split(":")[1] },
                { it.time.split(":")[2] },
                { it.originalName.length },
                { it.alias.length },
                { it.originalName },
                { it.alias })).joinToString("", "名字包含有 $name 的可建造舰船有:") {
                "船名：${it.originalName}[${it.alias}]\t建造时间：${it.time}\t${
                    when (it.limitedTime) {
                        1 -> "限时"
                        2 -> "建造绝版"
                        else -> "常驻"
                    }
                }\n"
            }
        }
    }
}