package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.AzurLaneLibrary.SQLiteLink

object Construction : SimpleCommand(
    AzurLaneLibrary, "Construction", "建造时间", description = "碧蓝航线建造时间查询"
) {
    override val usage: String = "${CommandManager.commandPrefix}建造时间 [时间|船名]\t#$description"

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
            else -> result.data.sortedWith(
                compareBy({ it.type },
                    { it.originalName.length },
                    { it.alias.length },
                    { it.originalName },
                    { it.alias })
            ).joinToString("\n", "建造时间为 $time 的舰船有:") {
                "船名：${it.originalName}[${it.alias}]-${it.rarity}\t${listType(it.type)}"
            }
        }
    }

    private fun nameToTime(name: String): String {
        val result = SQLiteLink.safeExecuteDQLorDCL<ConstructTimeData>(
            "SELECT * FROM ConstructTime WHERE originalName GLOB ? OR alias GLOB ?;", "*$name*", "*$name*"
        )

        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> "没有或尚未收录名字包含有 $name 的可建造舰船"
            else -> result.data.sortedWith(
                compareBy({ it.type },
                    { it.time.split(":")[0] },
                    { it.time.split(":")[1] },
                    { it.time.split(":")[2] },
                    { it.originalName.length },
                    { it.alias.length },
                    { it.originalName },
                    { it.alias })
            ).joinToString("\n", "名字包含有 $name 的可建造舰船有:") {
                "船名：${it.originalName}[${it.alias}]-${it.rarity}\t建造时间：${it.time}\t${listType(it.type)}"
            }
        }
    }

    private fun listType(type: Int): String {
        return when (type / 10) {
            7 -> "联/限/常"
            6 -> "联/限"
            5 -> "联/常"
            4 -> "联"
            3 -> "限/常"
            2 -> "限"
            1 -> "常"
            else -> ""
        } + when (type % 10) {
            7 -> "特/重/轻"
            6 -> "特/重"
            5 -> "特/轻"
            4 -> "特"
            3 -> "重/轻"
            2 -> "重"
            1 -> "轻"
            else -> ""
        }
    }
}