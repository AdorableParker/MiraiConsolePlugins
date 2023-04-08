package org.nymph

import kotlinx.serialization.Serializable

/**
 * @property triggerProbability 各群聊天触发概率
 * @property qaSheet 问答模板
 * @property invalidProblemFeedback 无回答反馈
 * @property prohibitedWord 各群违禁词
 * @property penaltyFeedback 惩罚反馈
 */
@Serializable
data class GroupSet(
    var triggerProbability: Int,
    val qaSheet: MutableSet<QAPair>,
    val invalidProblemFeedback: MutableSet<String>,
    val prohibitedWord: MutableSet<String>,
    val penaltyFeedback: MutableSet<String>
)