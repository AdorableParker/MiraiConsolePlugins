package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object DialogueData : AutoSavePluginData("AiTemplate") {

    @ValueDescription("初始化状态")
    var initialization: Boolean by value(true)

    @ValueDescription("违禁词")
    val prohibitedWord: MutableSet<String> by value(
        mutableSetOf()
    )

    @ValueDescription("聊天触发概率")
    val triggerProbability: MutableMap<Long, Int> by value(
        mutableMapOf()
    )

    @ValueDescription("惩罚反馈")
    val penaltyFeedback: MutableSet<String> by value(
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

    @ValueDescription("无回答反馈")
    val invalidProblemFeedback: MutableSet<String> by value(
        mutableSetOf("( -`_´- ) (似乎并没有听懂... ")
    )

    @ValueDescription("各群的问答模板")
    val QASheet: MutableMap<Long, MutableSet<QAPair>> by value(
        mutableMapOf()
    )
}