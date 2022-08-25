package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.nymph.AzurLaneLibrary.SQLiteLink
import org.nymph.AzurLaneLibrary.getResourceAsStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Birthday : SimpleCommand(
    AzurLaneLibrary, "Birthday", "舰娘生日",
    description = "历史今日下水舰船"
) {
    override val usage: String = "${CommandManager.commandPrefix}舰娘生日\t#$description"


    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("M月d日"))

        val result = SQLiteLink.executeDQLorDCL<BirthdayData> {
            "SELECT * FROM ShipBirthday WHERE launchDay == '$today'"
        }

        val message = when {
            result.error != null -> PlainText(result.error!!)
            result.data.isEmpty() -> PlainText("今天生日的舰娘没有记载哦")
            else -> buildMessageChain {
                result.data.forEach {
                    getResourceAsStream(it.path)?.uploadAsImage(group)?.let(::add)
                    add("${it.launchYear}年的今天,${it.name}下水")
                }
            }
        }
        sendMessage(message)
    }
}