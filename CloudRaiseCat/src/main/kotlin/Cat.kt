package org.nymph

import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.roundToInt

@Serializable
class Cat(var name: String, private val catType: String, val picUrl: String, private val uid: Long) {

    companion object {
        val CatType = mapOf(
            "阿比西尼亚猫" to "abys",
            "爱琴猫" to "aege",
            "美国短尾猫" to "abob",
            "美国卷耳猫" to "acur",
            "美国硬毛猫" to "awir",
            "美英猫" to "amau",
            "澳大利亚雾猫" to "amis",
            "巴厘岛猫" to "bali",
            "班比诺猫" to "bamb",
            "孟加拉虎" to "beng",
            "比尔曼猫" to "birm",
            "孟买猫" to "bomb",
            "英国长毛猫" to "bslo",
            "英国短毛猫" to "bsho",
            "博美拉猫" to "buri",
            "加州闪亮猫" to "cspa",
            "查达利/蒂法尼猫" to "ctif",
            "夏特鲁斯猫" to "char",
            "非洲狮子猫" to "chau",
            "奇多猫" to "chee",
            "重点色短毛猫" to "csho",
            "康沃尔-雷克斯猫" to "crex",
            "威尔士猫" to "cymr",
            "塞浦路斯猫" to "cypr",
            "德文狸猫" to "drex",
            "顿斯科伊猫" to "dons",
            "中国狸花猫" to "lihu",
            "埃及猫" to "emau",
            "缅甸猫" to "ebur",
            "异国短毛猫" to "esho",
            "哈瓦那褐猫" to "hbro",
            "喜马拉雅猫" to "hima",
            "日本短尾猫" to "jbob",
            "爪哇猫" to "java",
            "泰国御猫" to "khao",
            "呵叻猫" to "kora",
            "千岛短尾猫" to "kuri",
            "拉邦猫" to "lape",
            "缅因猫" to "mcoo",
            "马来猫" to "mala",
            "马恩岛猫" to "manx",
            "曼基康猫" to "munc",
            "内华达猫" to "nebe",
            "挪威森林猫" to "norw",
            "欧西猫" to "ocic",
            "波斯猫" to "pers",
            "北美洲短毛猫" to "pixi",
            "褴褛猫" to "raga",
            "布偶猫" to "ragd",
            "俄罗斯蓝猫" to "rblu",
            "沙凡那猫" to "sava",
            "苏格兰折耳猫" to "sfol",
            "塞尔凯克卷毛猫" to "srex",
            "暹罗猫" to "siam",
            "西伯利亚猫" to "sibe",
            "新加坡猫" to "sing",
            "雪鞋猫" to "snow",
            "索马里猫" to "soma",
            "斯芬克斯猫" to "sphy",
            "东京猫" to "tonk",
            "玩具虎猫" to "toyg",
            "土耳其安哥拉猫" to "tang",
            "土耳其梵猫" to "tvan",
            "约克巧克力猫" to "ycho"
        )
    }


    private var mood: Int = (1..100).random() // 心情
    private var weight: Double = (1..100).random() / 10.0  // 体重 单位 0.1斤
    private var satiety: Double = (1..900).random() / 10.0 // 饱食度
    private var foodBasin: Double = 0.0  // 单位 克
    private var workEnd: Long = 0 // 打工结束时间
    private var workTime: Int = 0
    var arenaTime: Long = 0 // 上次PK时间
    private var lastInteractionTime: Long = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)

    fun getWeight() = weight

    fun treatment(v: Int): Boolean = if (weight > v) {
        weight -= v; true
    } else false

    private fun eatFood(needEat: Double) {
        val canEaten = 100 - satiety
        foodBasin -= needEat
        if (canEaten < needEat) {  // 能吃下的 小于 需要吃的 吃能吃下的
            satiety = 100.0
        } else {              // 能吃下的 大于 需要吃的 吃需要吃的
            satiety += needEat
        }
    }

    fun upDataCatInfo(): String? {
        if (workEnd != 0L) {
            working() // 工作结算
            lastInteractionTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
            return null
        }
        /** 正常结算**/
        val subTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - lastInteractionTime
        val shouldEat = subTime / 1080.0 * 5.5
        /** 食物结算 **/
        if (foodBasin <= shouldEat) { // 食盆里面有的 小于 应该吃的 吃食盆里面的
            eatFood(foodBasin)
        } else {                      // 食盆里面有的 大于 应该吃的 吃应该吃的
            eatFood(shouldEat)
        }
        /** 消化结算 **/
        val shouldDigestion = subTime / 300.0 * 1.5 * (weight / 100.0)
        if (satiety >= shouldDigestion) {
            satiety -= shouldDigestion
            weight += shouldDigestion * mood
        } else {
            val consumptionFat = shouldDigestion - satiety
            satiety = 0.0
            weight += satiety * 0.95 - consumptionFat * 1.05
        }
        /** 心情结算 **/
        val moodChange = ((50 + satiety * 0.8 - weight * 0.3) / 50 * subTime / 300).toInt()
        mood = when {
            moodChange + mood > 100 -> 100
            moodChange + mood < 0 -> 0
            else -> moodChange + mood
        }
        lastInteractionTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        return when {
            weight > 200 -> "你的猫猫因为太肥，胖死惹"
            weight <= 0 -> "你的猫猫因为太久没有吃到饭，被饿死了"
            else -> null
        }
    }

    fun changeMood(v: Int) {
        mood = when {
            mood + v > 100 -> 100
            mood + v < 0 -> 0
            else -> mood + v
        }
    }

    fun addFood(food: Int) {
        changeMood((5..20).random())
        foodBasin += food
    }

    fun toWork(): String {
        workTime = (1..8).random()
        workEnd = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + 3600 * workTime
        return "$name 开始去打工了"
    }

    fun working(): Boolean {
        if (workEnd == 0L) return false
        return if (workEnd > LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) {
            true
        } else {
            val account = Account.user.getOrPut(uid) { UserAccount(0, 0, 200, 0) }
            eatFood((10..20).random() / 10.0) // 吃工作餐
            changeMood((-20..20).random()) // 工作心情变化
            workEnd = 0L
            account.gold += (10..workTime * 11).random() // 发工资
            false
        }
    }

    fun getCatInfo(): String {
        return upDataCatInfo()
            ?: "品种: $catType\n饱食度: ${(satiety * 100.0).roundToInt() / 100.0}\n心情: $mood\n体重: ${(weight * 100).roundToInt() / 100.0}\n状态: ${if (workEnd != 0L) "工作中" else "休息中"}\n猫碗中的剩余猫粮: ${(foodBasin * 100).roundToInt() / 100}"
    }
}