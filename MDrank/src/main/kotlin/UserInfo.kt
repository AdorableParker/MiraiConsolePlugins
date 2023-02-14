package org.nymph

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

data class UserInfo(
    val userName: String,
    val relativeLevel: Double,
    val records: Int,
    val lastUpdate: Long,
    val playRecords: List<Record>
) {

    fun formatInfo(): String {
        val t = SimpleDateFormat("yyyy-MM-dd a hh:mm E", Locale.CHINA).format(lastUpdate)
        val rls = when (relativeLevel) {
            in 0.0..3.0 -> "还需努力"
            in 3.0..5.0 -> "低于平均"
            in 5.0..7.0 -> "平均水平"
            in 7.0..9.0 -> "高于平均"
            in 9.0..11.0 -> "大佬水平"
            else -> "神佬水平"
        }
        val bestRecord = playRecords.sortedWith(
            compareBy(
                { it.parallelRank },
                { it.rank },
                { it.accuracy.toDouble() },
                { it.score },
            )
        ).subList(0, 5)

        return "$userName\n评分:$rls(${(relativeLevel * 100.0).roundToInt() / 100.0})\n记录数:${records}\n====================\n${
            bestRecord.joinToString(
                "\n--------------------\n"
            ) { it.printInfo() }
        }\n====================\n数据最后更新时间:$t"
    }
}