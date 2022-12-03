package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.isOperator
import org.nymph.SeIfHelpManagementData.enableReportingList


object SetEnableReporting : SimpleCommand(
    SelfHelpManagement, "SetEnableReporting", "设定入群播报",
    description = "修改本群入群播报状态"
) {
    override val usage = "${CommandManager.commandPrefix}设定入群播报\t#$description"


    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        if (user.permission.isOperator().not()) {
            sendMessage("权限不足吧")
            return
        }
        if(group.id in enableReportingList) {
            enableReportingList.add(group.id)
            sendMessage("入群播报开启")
        } else{
            enableReportingList.remove(group.id)
            sendMessage("入群播报关闭")
        }
    }
}