import net.mamoe.mirai.console.command.FriendCommandSenderOnMessage
import net.mamoe.mirai.console.command.GroupTempCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object SetApiKey : SimpleCommand(
    SearchByImage, "SetApiKey", "key设定",
) {

    @Handler
    suspend fun FriendCommandSenderOnMessage.main(apikey:String) {
        Data.sauceNAOKey = apikey
        sendMessage("key已设定")
    }

    @Handler
    suspend fun GroupTempCommandSenderOnMessage.main(apikey: String) {
        Data.sauceNAOKey = apikey
        sendMessage("key已设定")
    }

}