package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.DynamicPushData.subscriptionList
import org.nymph.DynamicPushData.timeStampOfDynamic

object SubscriptionDynamics: SimpleCommand(
    DynamicPush, "SubscriptionDynamics", "订阅动态",
    description = "订阅B站动态"
) {
    override val usage: String =
//        "${CommandManager.commandPrefix}订阅动态 [B站用户名]"
    "${CommandManager.commandPrefix}订阅动态 [UID]"

//    @Handler
//    suspend fun MemberCommandSenderOnMessage.main(name: String) {
//        if (group.botMuteRemaining > 0) return
//
//        val (uid,uname) = foundUser(name)
//        if(uid == -1) {
//            sendMessage("查无此人")
//            return
//        }
//
//        subscriptionList.getOrPut(uid){ mutableSetOf()}.add(group.id)
//        timeStampOfDynamic.getOrPut(uid){ 1L }
//
//        sendMessage("$uname($uid) 订阅成功")
//    }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(uid: Long) {
        if (group.botMuteRemaining > 0) return

        subscriptionList.getOrPut(uid) { mutableSetOf() }.add(group.id)
        timeStampOfDynamic.getOrPut(uid) { 1L }

        sendMessage("$uid 订阅成功")
    }
}