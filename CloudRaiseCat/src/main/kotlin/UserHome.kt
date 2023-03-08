package org.nymph

import kotlinx.serialization.Serializable

@Serializable
class UserHome(
    var food: Int = 0, // 食物数量  喂食扣除
    var liquid: Int = 0, // 猫条  撸猫扣除
    var cat: Cat? = null
)