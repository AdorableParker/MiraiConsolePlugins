package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object BuyCannedCat : SimpleCommand(
    CloudRaiseCat, "BuyCannedCat", "买猫粮", description = "购买猫罐头"
) {


    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val account = Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }

        if (1 > 100.0 - userHome.food) {
            sendMessage("你的家里已经堆不下猫罐头啦，快使用喂食命令给猫猫喂罐头吧")
            return
        }
        if (account.gold < 10) {
            sendMessage("一罐猫罐头售价10金币哦,你身上没有足够的钱,快去赚钱吧~")
            return
        }

        account.gold -= 100 // 付款
        userHome.food += 1
    }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(value: Int) {
        if (group.botMuteRemaining > 0) return
        val account = Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }

        if (value >= 0) {
            sendMessage("这里不回收猫罐头哦")
            return
        }
        if (value > 100.0 - userHome.food) {
            sendMessage("你的家里已经堆不下猫罐头啦，快使用喂食命令给猫猫喂罐头吧")
            return
        }
        if (account.gold < 10 * value) {
            sendMessage("一罐猫罐头售价10金币哦,你身上没有足够的钱,快去赚钱吧~")
            return
        }

        account.gold -= 10 * value // 付款
        userHome.food += value
    }
}