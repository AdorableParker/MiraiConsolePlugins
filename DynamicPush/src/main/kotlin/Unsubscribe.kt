package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.DynamicPushData.subscriptionList

object Unsubscribe : SimpleCommand(
    DynamicPush, "Unsubscribe", "取消订阅",
    description = "取消订阅B站动态"
) {
    override val usage: String =
//        "${CommandManager.commandPrefix}取消订阅 [B站用户名]"
    "${CommandManager.commandPrefix}取消订阅 [UID]"

//    @Handler
//    suspend fun MemberCommandSenderOnMessage.main(name: String) {
//        if (group.botMuteRemaining > 0) return

//        val (uid, uname) = foundUser(name)
//        if (uid == -1) {
//            sendMessage("查无此人")
//            return
//        }
//        subscriptionList[uid]?.remove(group.id)
//
//        sendMessage("$uname($uid) 取消订阅完成")
//    }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(uid: Long) {
        if (group.botMuteRemaining > 0) return

        subscriptionList[uid]?.remove(group.id)

        sendMessage("$uid 取消订阅完成")
    }
}