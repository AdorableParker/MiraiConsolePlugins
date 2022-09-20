package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import org.nymph.FFMPEG.getAudioData

object T2CS:SimpleCommand(
    Moegoe,"T2CS","说中文",
    description = "文本转中文"
) {
    private val speakers = listOf(
        "琴", "魈", "凝光", "北斗", "甘雨", "七七", "刻晴", "雷泽", "宵宫", "夜兰",
        "五郎", "莫娜", "申鹤", "行秋", "烟绯", "辛焱", "砂糖", "胡桃", "重云", "派蒙",
        "凯亚", "安柏", "丽莎", "香菱", "温迪", "可莉", "早柚", "托马", "优菈", "云堇",
        "钟离", "阿贝多", "班尼特", "久岐忍", "菲谢尔", "诺艾尔", "迪奥娜", "迪卢克",
        "芭芭拉", "雷电将军", "神里绫华", "神里绫人", "罗莎莉亚", "八重神子", "荒泷一斗",
        "九条裟罗", "达达利亚", "枫原万叶", "珊瑚宫心海", "鹿野院平藏"
    )
    override val usage = "${CommandManager.commandPrefix}说中文 [谁说] [说什么]\t#$description [语言支持:${speakers.joinToString()}]"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(who:String, what:String){
        if (group.botMuteRemaining > 0) return

        if (who !in speakers) {
            sendMessage("不支持这个语音哦")
            return
        }
        val cnApi = "http://233366.proxy.nscc-gz.cn:8888?speaker=$who&text=$what"
        when (val request = getAudioData(cnApi)) {
            is AudioData -> request.audio.toExternalResource().use { sendMessage(group.uploadAudio(it)) }
            is ErrData -> sendMessage(request.err)
        }
    }
}




