import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.FriendCommandSenderOnMessage
import net.mamoe.mirai.console.command.GroupTempCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand

object SetApiKey : SimpleCommand(
    SearchByImage, "SetApiKey", "key设定",
    description = "设定 SauceNAO API Key"
) {
    override val usage: String = "${CommandManager.commandPrefix}key设定 <apikey>\t#$description"
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