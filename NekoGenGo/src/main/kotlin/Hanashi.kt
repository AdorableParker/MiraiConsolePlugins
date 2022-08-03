package org.nymph

import nekoEncode.Huffman
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object Hanashi : SimpleCommand(NekoGenGo, "Hanashi", "喵译") {

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(original: String) {
        if (group.botMuteRemaining > 0) return
        sendMessage(Huffman.unzip(original))
    }
}