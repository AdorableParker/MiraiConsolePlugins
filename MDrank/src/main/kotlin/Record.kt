package org.nymph

import kotlin.math.roundToInt

class Record(
    val score: Int,
    val accuracy: Number,
    val rank: Int,
    val parallelRank: Int,
    songID: String,
    difficultyValue: Int,
    character_uid: Int,
    elfin_uid: Int
) {
    private val songName = MDAccountData.songList[songID]
    private val difficulty = arrayOf("新手", "高手", "大触").get(difficultyValue)
    private val character = arrayOf(
        "贝斯手-凛",
        "问题少女-凛",
        "梦游少女-凛",
        "兔女郎-凛",
        "飞行员-布若",
        "偶像-布若",
        "僵尸少女-布若",
        "华服小丑-布若",
        "提琴少女-玛莉嘉",
        "女仆-玛莉嘉",
        "魔法少女-玛莉嘉",
        "小恶魔-玛莉嘉",
        "黑衣少女-玛莉嘉",
        "圣诞礼物-凛",
        "制服少女-布若",
        "领航员-柚梅",
        "游戏主播-NEKO#ΦωΦ",
        "打工战士-凛",
        "红白巫女-博丽灵梦",
        "重生的少女-El_Clear",
        "修女-玛莉嘉",
        "黑白魔法使-雾雨魔理沙"
    ).get(character_uid)
    private val elfin = arrayOf(
        "喵斯",
        "安吉拉",
        "塔纳托斯",
        "Rabot-233",
        "小护士",
        "小女巫",
        "小龙女",
        "莉莉丝",
        "佩奇医生",
        "静音灵"
    ).get(elfin_uid)


    fun printInfo() = "$songName\n${difficulty}难度\n分数: $score - ${(accuracy.toDouble() * 100.0).roundToInt() / 100.0}%\n排名:#$rank(#$parallelRank)\n$character\t$elfin"
}