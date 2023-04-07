package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import org.nymph.SlotMachineData.NOfW
import org.nymph.SlotMachineData.TNOT
import org.nymph.SlotMachineData.jackpot
import java.text.DecimalFormat


object AutoBetting : SimpleCommand(
    SlotMachine, "autoBetting", "快速拉杆", description = "自动连拉三次"
) {
    override val usage: String = "${CommandManager.commandPrefix}快速拉杆 \t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val account = Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }
        if (account.gold <= 30) {
            sendMessage("金币不足(10金/杆)")
            return
        }
        account.gold -= 30
        jackpot += 30
        val slotMachine = SlotMachineCore()
        val bonus = slotMachine.quickBetting()
        jackpot -= bonus
        account.gold += bonus
        sendMessage("目前奖池金额:$jackpot\n累计中奖概率:${DecimalFormat("#.##%").format(NOfW/TNOT)}\n========\n${slotMachine.show()}\n获得奖金$bonus")
    }
}