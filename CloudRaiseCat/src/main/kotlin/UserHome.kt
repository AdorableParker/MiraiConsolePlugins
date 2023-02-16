package org.nymph

class UserHome(
    var food: Int = 0, // 食物数量  喂食扣除
    var litter: Double = 0.0, // 猫砂数量 定时扣除
    var liquid: Int = 0, // 猫条  撸猫扣除
    var cat: Cat? = null
)