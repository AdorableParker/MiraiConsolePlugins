package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object FeedTheCat : SimpleCommand(CloudRaiseCat, "FeedTheCat", "喂猫", description = "喂猫罐头") {

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }
        if (userHome.cat == null) {
            sendMessage("铲屎官你还没有属于你的主子喔,快去买一只吧!")
            return
        }
        val r = userHome.cat!!.upDataCatInfo()
        if (r.isNullOrEmpty()) {
            when {
                userHome.cat!!.working() -> sendMessage("你的猫猫还在努力打工哦")
                userHome.food <= 0 -> sendMessage("铲屎官你家里已经没有罐头了")
                else -> { // 一个猫罐头 等于 5份食物
                    userHome.cat!!.addFood(5)
                    userHome.food -= 1
                    sendMessage("你往 ${userHome.cat!!.name} 的猫碗里面加了一个猫罐头")
                }
            }
            return
        }
        userHome.cat = null
        sendMessage(r)
    }
}