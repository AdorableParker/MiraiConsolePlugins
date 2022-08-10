package org.nymph

import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.nameCardOrNick

object CubeCoinRank : SimpleCommand(
    PersonalAccount, "CubeCoinRank", "金币排行",
) {
    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        val accountList = Account.user.filter { (id, _) ->
            id in group.members
        }.toList().sortedBy { (_, account) ->
            account.cube
        }

        var serial = 0
        sendMessage(accountList.joinToString { (id, account) ->
            serial++
            "No$serial.${group[id]?.nameCardOrNick}\t${account.cube}\n"
        })
    }
}