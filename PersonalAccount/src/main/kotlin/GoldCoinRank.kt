package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.nameCardOrNick

object GoldCoinRank : SimpleCommand(
    PersonalAccount, "GoldCoinRank", "金币排行",
    description = "本群用户金币排行"
) {
    override val usage: String = "${CommandManager.commandPrefix}金币排行\t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val accountList = Account.user.filter { (id, _) ->
            id in group.members
        }.toList().sortedBy { (_, account) ->
            account.gold
        }

        var serial = 0
        sendMessage(accountList.joinToString { (id, account) ->
            serial++
            "No$serial.${group[id]?.nameCardOrNick}\t${account.gold}\n"
        })
    }
}