package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import org.nymph.FFMPEG.getAudioData

object T2JS : SimpleCommand(
    Moegoe, "T2JS", "说日语",
    description = "文本转日语"
) {
    private val speakers = mapOf("宁宁" to 0, "爱瑠" to 1, "芳乃" to 2, "茉子" to 3, "丛雨" to 4, "小春" to 5, "七海" to 6)
    override val usage = "${CommandManager.commandPrefix}说日语\t#$description [语言支持:${speakers.keys.joinToString()}]"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main(who: String, what: String) {
        if (group.botMuteRemaining > 0) return

        val id = speakers.getOrElse(who) {
            sendMessage("不支持这个语音哦")
            return
        }
        val jpApi = "https://moegoe.azurewebsites.net/api/speak?text=$what&id=$id"

        when (val request = getAudioData(jpApi)) {
            is AudioData -> request.audio.toExternalResource().use { sendMessage(group.uploadAudio(it)) }
            is ErrData -> sendMessage(request.err)
        }
    }
}



