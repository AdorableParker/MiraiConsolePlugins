package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object DialogueData : AutoSavePluginData("AiTemplate") {

    @ValueDescription("初始化状态")
    var initialization: Boolean by value(true)

    val PublicInvalidQuestionFeedback: MutableSet<String> by value(
        mutableSetOf(
            "( -`_´- ) (似乎并没有听懂...",
            "你在说什么怪话啊"
        )
    )
    val PublicPenaltyFeedback: MutableSet<String> by value(
        mutableSetOf(
            "给爷爬╰（‵□′）╯",
            "爬远点(ノ｀Д)ノ",
            "再您妈的见(#◠‿◠)",
            "给大佬递口球~(￣▽￣)~*",
            "可是，这值得吗(⊙o⊙)？",
            "一条指令，一切都索然无味┑(￣Д ￣)┍",
            "￣へ￣"
        )
    )

    @ValueDescription(
        """
         **
         * qaSheet 问答模板
         * triggerProbability 各群聊天触发概率
         * invalidProblemFeedback 无回答反馈
         * prohibitedWord 各群违禁词
         * penaltyFeedback 惩罚反馈
         **
         """
    )
    val groupConfiguration: MutableMap<Long, GroupSet> by value(
        mutableMapOf()
    )
}


