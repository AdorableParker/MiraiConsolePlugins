package org.nymph

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.info
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.utils.info
import java.time.LocalDateTime

object Bank : KotlinPlugin(JvmPluginDescription.loadFromResource()
//    JvmPluginDescription(
//        id = "org.nymph.bank",
//        name = "Bank",
//        version = "0.1.0",
//    ) {
//        author("parker")
//        info("""银行-TB插件子功能模块""")
//        dependsOn("org.nymph.personal-account:[0.1.0,2.0.0)")
//        dependsOn("org.nymph.job-broadcast:[0.1.0,2.0.0)")
//    }
) {
    override fun onEnable() {
        logger.info { "$info-已加载" }
        BankVault.reload()
        Savings.register()
        Withdraw.register()
        LogOn.register()

        PayrollEvent(
            intervals = 1_00_00,
            name = "发薪事件",
            synopsis = "更新储户存款"
        ).addJob(LocalDateTime.now().dayOfYear * 10000 + 1800)

        GlobalEventChannel.subscribeAlways<PayrollEvent> {
            val cardinality = BankVault.depositorDeposit / BankVault.depositorMap.size / 1000
            BankVault.depositorMap.forEach{ (_,depositor)->
                if (depositor.days>=10){
                    depositor.deposit += cardinality + 500 * depositor.level
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
    }
}