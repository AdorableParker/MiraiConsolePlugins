package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.utils.info
import java.time.LocalDateTime

object Bank : KotlinPlugin(JvmPluginDescription.loadFromResource()) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        BankVault.reload()
        Savings.register()
        Withdraw.register()
        LogOn.register()
        Audit.register()
        UpgradeAccount.register()

        PayrollEvent(
            intervals = 1_00_00,
            name = "发薪事件",
            synopsis = "更新储户存款"
        ).addJob(LocalDateTime.now().dayOfYear * 10000 + 1800)

        GlobalEventChannel.subscribeAlways<PayrollEvent> {
            if (BankVault.depositorMap.isEmpty()) return@subscribeAlways
            val cardinality = BankVault.depositorDeposit / 1000 / BankVault.depositorMap.size
            BankVault.depositorMap.forEach{ (_,depositor)->
                if (depositor.days>=10){
                    depositor.deposit += cardinality + 400 * depositor.level
                    depositor.days = 0
                } else {
                    depositor.days++
                }
            }
        }

    }

    override fun onDisable() {
        Savings.unregister()
        Withdraw.unregister()
        LogOn.unregister()
        Audit.unregister()
        UpgradeAccount.unregister()
    }
}