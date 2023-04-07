package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.nymph.Tarot.getResourceAsStream
import seed.Cycle
import seed.Seed

object Order : SimpleCommand(
    Tarot,"tarot","每日塔罗",
    description = "每日一塔罗"
) {
    override val usage: String = "${CommandManager.commandPrefix}每日塔罗\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {

        if (group.botMuteRemaining > 0) return

        val card = divineTarot(user.id)

        sendMessage(buildMessageChain {
            +PlainText("${card.side}\n${card.word}")
            +getResourceAsStream(card.path)!!.uploadAsImage(group)
        })
    }

    private fun divineTarot(uid: Long): TarotData {
        val brand = listOf(
            listOf("The Fool", "愚者", "生命的新阶段展开、有机会和改变发生", "莽撞、渴望自由、逃避责任"),
            listOf("The Magician", "魔术师", "沟通良好、有创意、点石成金、可以实现新计划", "不切实际、散慢无目标、狡猾、谎言和延迟"),
            listOf("The High Priestess", "女祭司", "用直觉和潜意识的智慧来寻求灵感", "忽略直觉用脑袋判断、缺乏前瞻性"),
            listOf("The Empress", "女王", "依靠创造力和丰富的直觉实现计划、成为具体的成果", "在关系中遭遇困难、无法付出努力、无法实现计划"),
            listOf("The Emperor", "皇帝", "透过自律和实际的努力达到成功", "缺乏训练、激情胜于智慧、情感压倒理智"),
            listOf("The Hierophant", "教皇", "屈服一有影响力的权威、臣服他人的智慧", "新的观点、强制性的观点、思想上的争战"),
            listOf("The Lovers", "恋人", "幸福的相遇、同心协力、情投意合、前途光明、作出一段关系的决定", "不切实际的期待和幻想、逃避更深的承诺或责任"),
            listOf("The Chariot", "战车", "因坚持而取得成功、状况在控制之下", "情绪失控、失败、挫折、障碍"),
            listOf("Strength", "力量", "拥有内在的力量来面对恐惧和生命中的问题", "无助、退缩、无法处理生活上的问题、负面情绪"),
            listOf("The Hermit", "隐者", "得到身体或心灵上的协助和指引、贵人、思而后行", "寂寞、孤立、觉得被排挤"),
            listOf("Wheel of Fortune", "命运之轮", "机会、好的改变", "重覆的模式、艰困的改变"),
            listOf("Justice", "正义", "诚实、负起责任、解决争议", "不诚实、不公平、推卸责任、偏见"),
            listOf("The Hanged Man", "倒吊人", "反省的时光、专注某理想或信仰、休息以习得内在智慧", "无法得到超越社会压力的自由、自私、角色扮演自我设限"),
            listOf("Death", "死神", "为旧事物划上休止符、接纳新的改变", "抗拒、和恐惧改变、过着单调、重覆的生活"),
            listOf("Temperance", "节制", "从过去的错误中学习、尽力而为、避免重蹈覆辙", "没有目的的行为、重覆相同的错误、缺乏自我控制"),
            listOf("The Devil", "恶魔", "被自己的恐惧阻碍了自己、控制、物质化的观点", "捐弃控制生命的需求、正视自己的黑暗面、尝试性的走向自由、做出选择"),
            listOf("The Tower", "塔", "打破旧有的价值结构、重建自己的生活", "拒绝改变、成长趋缓、被阻挠或监禁的持续感"),
            listOf("The Star", "星星", "光明的未来、希望、平静、和平、和潜意识沟通良好、灵感", "失去目标和心灵的连系、感伤、没有灵感、空虚"),
            listOf("The Moon", "月亮", "欺骗、有些事物隐而不见、必需去面对潜意识的恐惧", "固执、沉溺、害怕、恐惧症、重覆未被解决的事情"),
            listOf("The Sun", "太阳", "在各个领域都拥有快乐和成就、获得支持", "自信薄弱、害怕不足、对人生和创造性抱着竞争的态度、自尊受损"),
            listOf("Judgement", "审判", "由过去的经验习得清晰的智慧、面对再生和新的开始", "自以为是、对生命的看法狭隘、后悔、空虚、判断力不佳"),
            listOf("The World", "世界", "成功、自信、实现、内心满足、环游世界", "已成功但又失去、因为认知不够全然、可能有更艰难的挑战在后面"),
        ).random(Seed.getSeed(uid, Cycle.Day))

        return if ((0..100).random(Seed.getSeed(uid, Cycle.Day)) in 0..50)
            TarotData("判定！顺位-${brand[0]}(${brand[1]})", brand[2], "tarot/${brand[1]}-正.jpg")
        else
            TarotData("判定！逆位-${brand[0]}(${brand[1]})", brand[3], "tarot/${brand[1]}-逆.jpg")
    }
}