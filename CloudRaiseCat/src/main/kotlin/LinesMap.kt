package org.nymph

enum class LinesMap(val situation: String, val victory: String, val fail: String, val benefitCoefficient: Int) {
    GreatAdvantages("大优势对局", "不出意外的获胜了", "居然还是被Ta的猫猫打败了", 2),
    Advantage("优势对局", "最终把Ta的猫猫打败了", "还是没能打败Ta的猫猫", 3),
    WeakAdvantage("微弱优势", "抓住机会把Ta的猫猫打败了", "大意了，被Ta的猫猫打败了", 4),
    EquivalentStrength("势均力敌", "打败了Ta的猫猫", "被Ta的猫猫打败了", 5),
    WeakDisadvantage("微弱劣势", "一个偷袭,把Ta的猫猫打败了", "被Ta的猫猫顺势打败了", 6),
    Inferiority("劣势对局", "努力的把Ta的猫猫打败了", "最终被Ta的猫猫打败了", 7),
    GreatDisadvantage("大劣势对局", "居然把Ta的猫猫打败了", "不出意外的被打败了", 8),
    Special("特殊对局", "胜利宣言", "失败宣言", 1)
}