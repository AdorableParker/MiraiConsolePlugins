package org.nymph

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.jsoup.Jsoup

object Dynamic {
//    缺少 Key
//    fun foundUser(inName: String): Pair<Int, String> {
//
//        val doc =
//            Jsoup.connect("https://api.bilibili.com/x/web-interface/search/type?search_type=bili_user&keyword=$inName")
//                .ignoreContentType(true)
//                .execute().body().toString()
//        val jsonObj = Parser.default().parse(StringBuilder(doc)) as JsonObject
//        val userObj = jsonObj.obj("data")
//            ?.array<JsonObject>("result")?.get(0)
//
//        if (userObj.isNullOrEmpty()) return Pair(-1, "")
//
//        val uid = userObj.int("mid")!!
//        val name = userObj.string("uname")!!
//        return Pair(uid, name)
//    }

    fun getDynamic(uid: Long, index: Int, flag: Boolean = false): DynamicInfo {
        val doc = Jsoup.connect("https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?host_uid=$uid")
            .ignoreContentType(true)
            .execute().body().toString()
        val jsonObj = Parser.default().parse(StringBuilder(doc)) as JsonObject

        val dynamicObj = jsonObj.obj("data")?.array<JsonObject>("cards")?.let {
            it.getOrElse(index){
                return DynamicInfo(0, null, "")
            }
        } ?: return DynamicInfo(0, null, "")

        val desc = dynamicObj.obj("desc")
        val card = Parser.default().parse(StringBuilder(dynamicObj.string("card"))) as JsonObject
        val typeCode = desc?.int("type") ?: 0
        val timestamp = desc?.long("timestamp")?.times(1000) ?: 0

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