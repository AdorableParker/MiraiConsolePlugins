package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object SetProxy : SimpleCommand(AcgImage, "SetProxy", "设定代理域名") {

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(proxy: String) {
        if (group.botMuteRemaining > 0) return

        Data.proxy = proxy
        sendMessage("设定代理完成")
    }
}