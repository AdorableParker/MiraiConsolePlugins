package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.AzurLaneLibrary.SQLiteLink


object ShipMap : SimpleCommand(
    AzurLaneLibrary, "ShipMap", "打捞定位",
    description = "碧蓝航线舰船打捞定位"
) {
    override val usage = "${CommandManager.commandPrefix}打捞定位 <船名|地图坐标>\t#$description"


    @Handler
    suspend fun MemberCommandSenderOnMessage.main(uncheckedIndex: String) {
        if (group.botMuteRemaining > 0) return


        val pretreatmentIndex = uncheckedIndex.replace("—", "-").toCharArray()
        pretreatmentIndex.forEachIndexed { index, char ->
            if (char.isLowerCase()) pretreatmentIndex[index] = char.uppercaseChar()
        }
        val treatedIndex = String(pretreatmentIndex)
        val index = Regex("\\d*?-\\d").find(treatedIndex)?.value?.let(::mapToName) ?: nameToMap(treatedIndex)
        sendMessage(index)
    }

    private fun format(code: Long): String {
        var chapter = 0
        var c = code
        return buildString {
            while (c >= 16) {
                val remainder = c % 16
                c /= 16
                chapter++
                if (remainder and 1L == 1L) append("$chapter-1\t")
                if (remainder and 2L == 2L) append("$chapter-2\t")
                if (remainder and 4L == 4L) append("$chapter-3\t")
                if (remainder and 8L == 8L) append("$chapter-4\t")
                append('\n')
            }
        }
    }

    private fun nameToMap(name: String): String {
        val result = SQLiteLink.safeExecuteDQLorDCL<ShipMapData>(
            "SELECT * FROM ShipMap WHERE originalName GLOB ? OR alias GLOB ?;", "*$name*", "*$name*"
        )
        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> "没有或尚未收录名字包含有 $name 的可打捞舰船"
            else -> result.data.sortedWith(
                compareBy(
                    { it.special.length },
                    { it.rarity.length },
                    { it.originalName.length },
                    { it.alias.length },
                    { it.originalName },
                    { it.alias }
                )
            ).joinToString("\n", "名字包含有 $name 的可打捞舰船有:\n") {
                "船名：${it.originalName}[${it.alias}]-${it.rarity}\t可打捞地点:\n${format(it.mapCode)} ${it.special}"
            }
        }
    }

    private fun mapToName(index: String): String {
        val coordinate = index.split("-")
        val result = SQLiteLink.executeDQLorDCL<ShipMapData> {
            "SELECT * FROM ShipMap WHERE mapCode>> 4*(${coordinate[0]}-1)+${coordinate[1]}-1 & 1 == 1"
        }
        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> "没有记录主线图坐标为 $index 的地图"
            else -> result.data.sortedWith(
                compareBy(
                    { it.rarity },
                    { it.originalName.length },
                    { it.alias.length },
                    { it.originalName },
                    { it.alias }
                )
            ).joinToString("\n", "可在 $index 打捞的舰船有:\n") { "${it.originalName}[${it.alias}]-${it.rarity}" }
        }
    }
}