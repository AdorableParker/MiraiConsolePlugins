package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.AzurLaneLibrary.SQLiteLink

object Roster : SimpleCommand(
    AzurLaneLibrary, "Roster", "船名查询", "和谐名",
    description = "碧蓝航线船名查询"
) {
    override val usage: String = "${CommandManager.commandPrefix}船名查询 [船名]\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(shipName: String) {
        if (group.botMuteRemaining > 0) return
        sendMessage(encodeName(shipName.uppercase()))
    }

    private fun encodeName(shipName: String): String {
        val result = SQLiteLink.safeExecuteDQLorDCL<RosterData>(
            "SELECT * FROM Roster WHERE code GLOB ? OR name GLOB ?;", "*$shipName*", "*$shipName*",
        )

        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> "没有或尚未收录名字包含有 $shipName 的舰船"
            else -> result.data.sortedWith(
                compareBy(
                    { it.name.length },
                    { it.name }
                )
            ).joinToString("\n", "名字包含有 $shipName 的舰船有:") {
                "原名：${it.name}\t和谐名：${it.code}"
            }
        }
    }
}