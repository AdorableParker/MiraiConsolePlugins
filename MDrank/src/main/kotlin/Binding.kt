package org.nymph

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.message.data.content
import org.nymph.MDApi.getUserList


object Binding : SimpleCommand(
    MDrank, "binding", "绑定",
    description = "绑定默认账号"
) {
    override val usage: String = "${CommandManager.commandPrefix}绑定 <用户名>"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(userName: String) {
        if (group.botMuteRemaining > 0) return

        val userList = getUserList(userName)
        val userAccount = when {
            userList.first.size > 1 -> {
                sendMessage(buildMessageChain {
                    +"找到多个账号,请输入序号选择\n"
                    for (v in 1..userList.first.size) {
                        +"$v:${userList.first[v - 1]}\n"
                    }
                })

                val judge = GlobalEventChannel.asFlow().filterIsInstance<GroupMessageEvent>()
                    .filter { it.sender.id == user.id }.map { it.message.content.toIntOrNull() }.first() ?: 0

                if (judge in 1..userList.first.size) userList.second[judge - 1]
                else {
                    sendMessage("无效序号,选择失败")
                    return
                }
            }
            userList.first.size == 1 -> userList.second[0]
            else -> {
                sendMessage("没有查询到该账号信息，绑定失败")
                return
            }
        }
        MDAccountData.MDAccountMap[user.id] = userAccount
        sendMessage("账号绑定完成")
    }
}