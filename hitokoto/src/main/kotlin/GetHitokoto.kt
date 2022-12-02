package org.nymph

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.jsoup.Jsoup

object GetHitokoto : SimpleCommand(
    Hitokoto, "hitokoto", "一言",
    description = "一言"
) {
    override val usage: String = "${CommandManager.commandPrefix}签到"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        sendMessage(hitokoto())
    }


    private fun hitokoto():String {
        val doc = Jsoup.connect("https://v1.hitokoto.cn/")
            .ignoreContentType(true)
            .execute().body().toString()
        val jsonObj = Parser.default().parse(StringBuilder(doc)) as JsonObject
        val hitokoto = jsonObj.string("hitokoto")
        val form = jsonObj.string("from")
        return if (hitokoto.isNullOrEmpty() && form.isNullOrEmpty())  "一言-获取失败" else "『$hitokoto』\n——《$form》"
    }

}