package org.nymph

import nekoEncode.Huffman
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Hanashi : SimpleCommand(NekoGenGo, "Hanashi", "喵译",
    description = "将喵语解码"
) {
    override val usage: String = "${CommandManager.commandPrefix}喵译 [喵语]\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(original: String) {
        if (group.botMuteRemaining > 0) return
        sendMessage(Huffman.unzip(original))
    }
}