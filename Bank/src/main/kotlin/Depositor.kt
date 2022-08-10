package org.nymph

import kotlinx.serialization.Serializable

@Serializable
data class Depositor(
    /** 存款 */
    var deposit:Int,
    /** 账户等级 */
    var level:Int,
    /** 发薪倒计时 */
    var days:Int
)
