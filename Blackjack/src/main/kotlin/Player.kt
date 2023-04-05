package org.nymph

import kotlinx.serialization.Serializable

@Serializable
class Player(
    val playerID: Long,
    val ante: Int = 100,
    val handCard: MutableList<String> = mutableListOf(),
    var odds: Double = -1.0,
    var nowPoint: Int = 0,
) {

    fun sum(): Int {
        var countOfA = 0
        val count = handCard.fold(0) { sum, element ->
            sum + when (element) {
                "2", "3", "4", "5", "6", "7", "8", "9", "10" -> element.toInt()
                "J", "Q", "K" -> 10
                else -> {
                    countOfA++
                    1
                }
            }
        }
        nowPoint = count + if (countOfA != 0 && count <= 11) 10 else 0
        return nowPoint
    }
}
