package org.nymph

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.isRegistered
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.message.sourceTime
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.error
import okhttp3.OkHttpClient
import okhttp3.Request
import org.nymph.Data.proxy
import java.net.SocketTimeoutException
import java.net.URL


object DailyImage : SimpleCommand(
    AcgImage, "DailyImage", "随机图片",
    description = "获取随机图片"
) {

    override val usage = "${CommandManager.commandPrefix}随机图片 [数量] [标签]\t#$description"
    private val json = Json { ignoreUnknownKeys = true }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(count: Int = 1, tag: String? = null) {
        if (group.botMuteRemaining > 0) return

        val account = Account.user.getOrPut(user.id) { UserAccount(200, 0, 200, 0) }
        if (account.gold < 100 * count) {
            sendMessage("账户金币不足(100金币/张)")
            return
        }

        val (err, doc) = getImageData(
            "https://api.lolicon.app/setu/v2?r18=0&size=regular&proxy=${proxy}&num=$count${if (tag != null) "&tag=$tag" else ""}"
        )
        if (err != null) {
            sendMessage(err)
            return
        }
        val obj = runCatching {
            json.decodeFromString<DocData>(doc)
        }.onFailure {
            sendMessage("Json 解析失败:\n${it.message}")
            AcgImage.logger.error { doc }
            return
        }.getOrThrow()
        if (obj.error.isNotEmpty()) {
            sendMessage(obj.error)
            return
        }

        if (obj.data.isEmpty()) {
            sendMessage("没有找到匹配结果,你的xp是不是太怪了")
            return
        }

        for (data in obj.data) {
            account.gold -= 100
            sendMessage(buildMessageChain {
                +"标题:${data.title}[${data.pid}]\n"
                +"作者:${data.author}[${data.uid}]\n"
                +"原图尺寸:${data.width}:${data.height}\n"
                +"标签:${data.tags}\n"
                +URL(data.urls.regular).openConnection().getInputStream().use { it.uploadAsImage(group) }
            })
        }
    }

    private fun getImageData(url: String): Pair<String?, String> {
        return runCatching {
            val client = OkHttpClient().newBuilder().build()
            val request: Request = Request.Builder().url(url).method("GET", null).build()
            Pair(null, client.newCall(request).execute().body!!.string())
        }.onFailure {
            Pair(
                when (it) {
                    is SocketTimeoutException -> "远端服务器超时或无响应,请稍后再试"
                    else -> "请求异常\t${it.message}"
                }, ""
            )
        }.getOrThrow()
    }
}