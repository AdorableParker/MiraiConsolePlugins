package org.nymph

import org.nymph.SlotMachineData.jackpot
import kotlin.math.pow

class SlotMachineCore {
    private val iconOrder = arrayOf(1, 2, 3, 4, 5, 6, 7)
    private val turntable = mutableListOf<List<Int>>()


    fun betting(revise: Int) {
        val base = (7..70).random() + revise
        iconOrder.shuffle()
        turntable.add(listOf(iconOrder[base % 7], iconOrder[(base + 1) % 7], iconOrder[(base + 2) % 7]))
    }

    fun settleAccounts(): MutableList<Int> {
        val base = mutableListOf(0, 0)
        for (index in 0..2) {
            if (turntable[0][index] == turntable[1][index]) {
                // 三连 取奖池的10% 二连 取2的次方份的赌注
                if (turntable[1][index] == turntable[2][index]) base[0]++ else base[1]++
            }
        }
        when (turntable[1][1]) {
            turntable[0][0] -> if (turntable[2][2] == turntable[0][0]) base[0]++
            turntable[2][0] -> if (turntable[0][2] == turntable[2][0]) base[0]++
        }

        return base
    }

    fun quickBetting(): Int {
        betting(0)
        betting(0)
        betting(0)

        val base = settleAccounts()
        return (base[0] * 0.1 * jackpot + if (base[1] != 0) 2.0.pow(base[1]) * 10 else 0.0).toInt()
    }

    fun show() = """    
        ${turntable[0][0]} ${turntable[1][0]} ${turntable[2][1]}
        ${turntable[0][1]} ${turntable[1][1]} ${turntable[2][1]}
        ${turntable[0][2]} ${turntable[1][2]} ${turntable[2][2]}
        --- --- ---
        """.trimIndent()
}
