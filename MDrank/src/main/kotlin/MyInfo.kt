package org.nymph

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
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
import org.jsoup.Jsoup
import org.nymph.MDApi.getUserList


object MyInfo : SimpleCommand(
    MDrank, "MyInfo", "数据",
    description = "获取账号数据"
) {
    override val usage: String = "${CommandManager.commandPrefix}数据 [用户名]"

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
                sendMessage("没有查询到该账号信息")
                return
            }
        }
        sendMessage(getUserInfo(userAccount))
    }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val userAccount = MDAccountData.MDAccountMap.get(user.id)
        if (userAccount == null) sendMessage("尚未绑定账号,请先绑定")
        else sendMessage(getUserInfo(userAccount))
    }

    private fun getUserInfo(userID: String): String {
        val doc = Jsoup.connect("https://api.musedash.moe/player/$userID")
            .ignoreContentType(true)
            .execute().body().toString()
        val jsonObj = Parser.default().parse(StringBuilder(doc)) as JsonObject
        val plays = jsonObj.array<JsonObject>("plays")!!
        val info = UserInfo(
            jsonObj.obj("user")!!.string("nickname")!!,
            jsonObj.double("rl")!!,
            plays.size,
            jsonObj.long("lastUpdate")!!,
            List(plays.size) {
                Record(
                    plays[it].int("score")!!,
                    plays[it].get("acc") as Number,
                    plays[it].int("i")!!+1,
                    plays[it].int("sum")!!+1,
                    plays[it].string("uid")!!,
                    plays[it].int("difficulty")!!,
                    plays[it].string("character_uid")!!.toInt(),
                    plays[it].string("elfin_uid")!!.toInt()
                )
            }
        )
        return info.formatInfo()
    }
}