package org.nymph

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.Member
import org.nymph.LinesMap.*
import java.time.LocalDateTime
import java.time.ZoneOffset

object CatPK : SimpleCommand(
    CloudRaiseCat, "CatPK", "猫猫PK", description = "猫猫PK"
) {
    override val usage: String = "${CommandManager.commandPrefix}猫猫PK \t#$description"
    @Handler
    suspend fun MemberCommandSenderOnMessage.main(target: Member) {
        if (group.botMuteRemaining > 0) return
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }

        val uhCat = userHome.cat
        if (uhCat == null) {
            sendMessage("铲屎官你还没有属于你的猫猫哦，快去猫店买一只吧")
            return
        }
        val r1 = uhCat.upDataCatInfo()

        if (r1.isNullOrEmpty() && uhCat.working()) {
            sendMessage("你的猫猫还在努力打工哦")
            return
        }

        val targetHome = CloudCatData.cloudCatList.getOrPut(target.id) { UserHome() }
        val thCat = targetHome.cat
        if (thCat == null) {
            sendMessage("Ta还没有属于自己的猫猫哦，没办法pk呢")
            return
        }
        val r2 = thCat.upDataCatInfo()
        if (r2.isNullOrEmpty()) {
            if (uhCat.working()) sendMessage("Ta的猫猫还在努力打工哦") else sendMessage(uhCat.toWork())
            return
        }
        if (uhCat.arenaTime + 600 >= LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)){
            sendMessage("你的猫猫才pk没多久,现在不想pk哦")
            return
        }else{
            uhCat.arenaTime =  LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        }

        val pkr: PKResult = when (uhCat.getWeight() - thCat.getWeight()) {
            in 180.0..200.0 -> SpecialResults(1, "${uhCat.name} 以压倒性的体重战胜了Ta的猫猫")
            in -200.0..-180.0 -> SpecialResults(9, "Ta的猫猫以压倒性的体重战胜了${uhCat.name}")
            in 80.0..180.0 -> NormalResults(GreatAdvantages, -20..80)
            in -180.0..-80.0 -> NormalResults(GreatDisadvantage, -80..20)
            in 20.0..80.0 -> NormalResults(Advantage, -35..60)
            in -80.0..-20.0 -> NormalResults(Inferiority, -65..35)
            in 5.0..20.0 -> NormalResults(WeakAdvantage, -45..55)
            in -20.0..-5.0 -> NormalResults(WeakDisadvantage, -55..45)
            in -5.0..5.0 -> NormalResults(EquivalentStrength, -50..50)
            else -> SpecialResults(5, "这不正常的体重比,一定是有人作弊了,这次PK成绩无效")
        }

        val gold =
            ((pkr.situationLevel.benefitCoefficient + 1) * 10..(pkr.situationLevel.benefitCoefficient + 1) * 15).random()
        val emaciation = (1..pkr.situationLevel.benefitCoefficient).random()

        val account = Account.user.getOrPut(user.id) { UserAccount(0, 0, 200, 0) }
        if (pkr is SpecialResults) {
            when (pkr.situation) {
                9 -> {
                    account.gold += gold
                    sendMessage("${pkr.results},你家的猫猫为你赢得了${gold}枚金币")
                }

                1 -> if (uhCat.treatment(emaciation)) {
                    sendMessage("${pkr.results},你的猫猫在医疗中心治愈过程中体重降低了${emaciation * 125 / 500}斤")
                } else {
                    sendMessage("${pkr.results},你的猫猫在医疗中心抢救无效死掉惹")
                    userHome.cat = null
                }

                5 -> sendMessage(pkr.results)
            }
            return
        }
        if (pkr is NormalResults) {
            val sl = pkr.situationLevel
            if (pkr.judgmentInterval.random() >= 0) {
                account.gold += gold
                sendMessage("在${sl.situation}的情况下,${uhCat.name} ${sl.victory},你家的猫猫为你赢得了${gold}枚金币")
            } else {
                uhCat.treatment(emaciation)
                sendMessage("在${sl.situation}的情况下,${uhCat.name} ${sl.fail},你的猫猫在医疗中心治愈过程中体重降低了${emaciation * 125 / 500}斤")
            }
        }

    }
}