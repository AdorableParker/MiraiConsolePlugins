package org.nymph

import nekoEncode.Huffman
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Honyaku : SimpleCommand(NekoGenGo, "Honyaku", "喵说",
    description = "将原文编码为喵语"
) {
    override val usage: String = "${CommandManager.commandPrefix}喵说 [原文]\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(original: String) {
        if (group.botMuteRemaining > 0) return
        sendMessage(Huffman.zip(original))
    }
}