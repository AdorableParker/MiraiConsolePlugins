import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.event.EventPriority
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.syncFromEvent
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import Data.sauceNAOKey
import java.net.SocketTimeoutException


object SauceNAO : SimpleCommand(
    SearchByImage, "SauceNAO", "搜图",
    description = "以图搜图"
) {
    override val usage: String = "${CommandManager.commandPrefix}搜图 [图片]"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(image: Image) {
        if (group.botMuteRemaining > 0) return

        sendMessage("开始查询，请稍后...")
        sendMessage(getResults(image.queryUrl()))
    }

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        sendMessage("请在60秒内提供要搜的图...")
        val image = withTimeoutOrNull(60_000) {
            GlobalEventChannel.syncFromEvent<GroupMessageEvent, Image>(EventPriority.MONITOR) {
                if (user == it.sender) it.message[Image] else null
            }
        }

        if (image != null) main(image) else sendMessage("长时间未收到图片,搜图失败")
    }

    private fun getResults(img: String): String {
        if (sauceNAOKey.isBlank()) return "请提供APIKey后使用(在私聊环境使用 key设定 命令提供apiKey)"
        val url = "https://saucenao.com/search.php?output_type=2&numres=1&db=999&api_key=$sauceNAOKey&url=$img"
        val doc = runCatching {
                val client = OkHttpClient().newBuilder().build()
                val request = Request.Builder().url(url).method("GET", null).build()
                client.newCall(request).execute().body.toString()
            }.onFailure {
                return when(it){
                    is SocketTimeoutException -> "远端服务器超时或无响应,请稍后再试"
                    else -> "请求异常"
                }
            }.getOrThrow()

        val format = Json { ignoreUnknownKeys = true }
        val obj = format.decodeFromString<DocData>(doc)
        if (obj.header.uid <=0) return "远端服务器运行异常,请稍后再试"
        if (obj.header.status != 0) return obj.header.message
        if (obj.header.numberOfResults <= 0) return "没有找到匹配结果"
        if (obj.results.isEmpty()) return "无效结果返回"
        return if (obj.results[0].data.extUrls.isEmpty()) "相似度：${obj.results[0].header.similarity}\n来源库：${obj.results[0].header.source()}\n相关链接：未能获得\n详细源数据：${obj.results[0].data}\n24小时内剩余搜索次数：${obj.header.remain}"
        else "相似度：${obj.results[0].header.similarity}\n来源库：${obj.results[0].header.source()}\n相关链接：${obj.results[0].data.extUrls}\n24小时内剩余搜索次数：${obj.header.remain}"
    }
}


