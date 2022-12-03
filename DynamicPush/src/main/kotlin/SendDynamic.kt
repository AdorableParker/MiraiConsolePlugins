package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.Dynamic.getDynamic
import org.nymph.DynamicPushSetting.DynamicNameList


object SendDynamic : SimpleCommand(
    DynamicPush, "SendDynamic", "动态查询",
    description = "B站动态查询"
) {
    override val usage: String =
        "${CommandManager.commandPrefix}动态查询 [简称|UID] <回溯条数>\n简称列表：\n${DynamicNameList.keys.joinToString("\t")}"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(name: String, index: Int = 0) {
        if (group.botMuteRemaining > 0) return
        sendMessage(formatDynamic(DynamicNameList.getOrDefault(name, name.toLongOrNull()), index))
    }

    private fun formatDynamic(uid: Long?, index: Int): String {
        if (uid == null) return usage
        return if (index >= 10) {
            "最多只能往前10条哦\n(￣﹃￣)"
        } else if (index < 0) {
            "未来的事情我怎么会知道\n=￣ω￣="
        } else {
            val cache = getDynamic(uid, index)
            "${cache.name}:\n${cache.text}"
        }
    }


}