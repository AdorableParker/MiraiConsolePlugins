package org.nymph

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.jsoup.Jsoup
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
        sendMessage(formatDynamic(DynamicNameList.getOrDefault(name, name.toIntOrNull()), index))
    }

    private fun formatDynamic(uid: Int?, index: Int): String {
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

    fun getDynamic(uid: Int, index: Int, flag: Boolean = false): DynamicInfo {
        val doc = Jsoup.connect("https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?host_uid=$uid")
            .ignoreContentType(true)
            .execute().body().toString()
        val jsonObj = Parser.default().parse(StringBuilder(doc)) as JsonObject
        val dynamicObj = jsonObj.obj("data")
            ?.array<JsonObject>("cards")?.get(index)
        val desc = dynamicObj?.obj("desc")!!
        val card = Parser.default().parse(StringBuilder(dynamicObj.string("card"))) as JsonObject
        val typeCode = desc.int("type") ?: 0
        val timestamp = desc.long("timestamp")?.times(1000) ?: 0

        if (flag) {
            val oldTime = DynamicPushData.timeStampOfDynamic[uid] ?: 0
            if (oldTime >= timestamp) return DynamicInfo(0, null, "")
            DynamicPushData.timeStampOfDynamic[uid] = timestamp
        }

        return analysis(timestamp, typeCode, card)
    }

    private fun analysis(timestamp: Long, typeCode: Int, card: JsonObject): DynamicInfo {
        return when (typeCode) {
            // 无效数据
            0 -> DynamicInfo(
                timestamp = timestamp,
                name = card.obj("user")?.string("name"),
                text = "没有相关动态信息",
            )
            // 转发
            1 -> {
                val origType = card.obj("item")?.int("orig_type") ?: 0
                val origin = Parser.default().parse(StringBuilder(card.string("origin"))) as JsonObject
                val originDynamic = analysis(timestamp, origType, origin)
                DynamicInfo(
                    timestamp = timestamp,
                    name = card.obj("user")?.string("uname"),
                    text = "转发并评论：\n${card.obj("item")?.string("content")}\n\n${originDynamic.text}"
                )
            }
            // 含图动态
            2 -> {
                val description = card.obj("item")?.string("description")   // 描述
                // 获取所有图片
//                val pictures = card.obj("item")?.array<JsonObject>("pictures")
                DynamicInfo(
                    timestamp = timestamp,
                    name = card.obj("user")?.string("name"),
                    text = "$description"
                )
            }
            // 无图动态
            4 -> DynamicInfo(
                timestamp = timestamp,
                name = card.obj("user")?.string("uname"),
                text = "${card.obj("item")?.string("content")}"
            )
            // 视频
            8 -> {
                val dynamic = card.string("dynamic") // 描述
                DynamicInfo(
                    timestamp = timestamp,
                    name = card.obj("owner")?.string("name"),
                    text = "视频投稿:$dynamic"
                )
            }
            // 专栏
            64 -> {
                val title = card.string("title")       // 标题
                val summary = card.string("summary")   // 摘要
//                val imgSrc = card.array<String>("image_urls")?.get(0) // 封面图片

                DynamicInfo(
                    timestamp = timestamp,
                    name = card.obj("author")?.string("name"),
                    text = "专栏标题:$title\n专栏摘要：\n$summary…"
                )
            }
            // 卡片
            2048 -> {
                val title = card.obj("sketch")?.string("title")          // 标题
                val context = card.obj("vest")?.string("content")        // 内容
                val targetURL = card.obj("sketch")?.string("target_url") // 相关链接
                DynamicInfo(
                    timestamp = timestamp,
                    name = card.obj("user")?.string("name"),
                    text = "动态标题:$title\n动态内容：\n$context\n相关链接:\n$targetURL"
                )
            }
            // 未知类型
            else -> {
                DynamicPush.logger.warning("File:SendDynamic.kt\tLine:162\n错误信息:未知的类型码 $typeCode ")
                DynamicInfo(
                    timestamp = timestamp,
                    name = card.obj("user")?.string("name"),
                    text = "是未知的动态类型,无法解析"
                )
            }
        }
    }
}