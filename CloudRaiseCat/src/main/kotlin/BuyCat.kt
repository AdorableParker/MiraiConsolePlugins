package org.nymph

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.utils.debug
import org.jsoup.Jsoup

object BuyCat : SimpleCommand(
    CloudRaiseCat, "BuyCat", "买猫", description = "购买一只猫猫"
) {
    override val usage: String = "${CommandManager.commandPrefix}买猫 \t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return
        val userHome = CloudCatData.cloudCatList.getOrPut(user.id) { UserHome() }
        val uhCat = userHome.cat
        if (uhCat != null) {
            sendMessage("你居然背着你家喵喵出来找小三!")
            when ((1..100).random()) {
                in 1..5 -> {
                    userHome.food = 0
                    userHome.cat = null
                    sendMessage("喔,天啊!你家猫猫很生气，带着所有猫粮离家出走了!\n你失去了所有!")
                }

                in 5..15 -> {
                    userHome.cat = null
                    sendMessage("你家猫猫很生气,它离家出走了!\n你失去了猫猫!")
                }

                else -> {
                    uhCat.changeMood(-10)
                    sendMessage("你家猫猫很生气!(心情-10)")
                }
            }
            return
        }


        val account = Account.user.getOrPut(user.id) { UserAccount(200, 0, 200, 0) }
        if (account.gold < 100) {
            sendMessage("一只喵喵售价100金币哦,你身上没有足够的钱,快去赚钱吧~")
            return
        }

        val candidate = mutableSetOf<String>()
        while (candidate.size < 5) {
            candidate.add(Cat.CatType.keys.random())
        }
        val candidateList = candidate.toList()
        sendMessage(
            candidateList.joinToString(
                "\n",
                "猫猫100金币一只,现在猫店里面有:\n",
                "\n你要购买那种猫呢(回复序号选择)"
            ) { "${candidateList.indexOf(it) + 1} - $it" }
        )
        val judge = GlobalEventChannel.asFlow().filterIsInstance<GroupMessageEvent>().filter { it.sender.id == user.id }
            .map { it.message.content.toIntOrNull() }.first() ?: 0
        if (judge > candidateList.size || judge <= 0) {
            sendMessage("序号错误，没有这个猫猫")
            return
        }
        sendMessage("为你的猫猫取个名字就可以带走了(回复名字即可)")
        val catName =
            GlobalEventChannel.asFlow().filterIsInstance<GroupMessageEvent>().filter { it.sender.id == user.id }
                .map { it.message.content }.first()
        if (catName.isBlank()) {
            sendMessage("没给猫猫取名字，猫猫不会理你的哦")
            return
        }

        val thisCat = getCatInfo(candidateList[judge - 1], catName, user.id)
        val r = when ((1..100).random()) {
            in 1..10 -> {
                thisCat.changeMood(-5)
                "这只猫猫好像不是很喜欢这个名字，恭喜你买了一只猫猫"
            }

            in 81..100 -> {
                thisCat.changeMood(5)
                "这只猫猫好像很喜欢这个名字，恭喜你买了一只猫猫"
            }

            else -> "恭喜你买了一只猫猫"
        }

        when ((1..100).random()) {
            in 1..10 -> {
                userHome.food += 5
                "$r,而且猫店赠送了你5个猫罐头"
            }

            in 81..100 -> {
                userHome.liquid += 2
                "$r,而且猫店赠送了你2支猫条"
            }

            else -> r
        }.let { sendMessage(it) }

        account.gold -= 100 // 付款
        userHome.cat = thisCat
    }

    private fun getCatInfo(catType: String, name: String, uid: Long): Cat {
        val doc1 = Jsoup.connect("https://api.thecatapi.com/v1/images/search?breed_ids=${Cat.CatType[catType]}")
            .ignoreContentType(true)
            .execute().body().toString()
        val jsonObj = Parser.default().parse(StringBuilder(doc1)) as JsonArray<*>
        for (i in jsonObj) {
            CloudRaiseCat.logger.debug { i.toString() }
        }
        val catObj = jsonObj[0] as JsonObject
        val picUrl = catObj.string("url").toString()
        //
//        val catId = jsonObj.string("id").toString()

//        if (catId.isEmpty() || picUrl.isEmpty()) return null
//        val doc2 = Jsoup.connect("https://api.thecatapi.com/v1/images/$catId")
//            .ignoreContentType(true)
//            .execute().body().toString()
//        val catInfo = Parser.default().parse(StringBuilder(doc2)) as JsonArray<*>


        return Cat(name, catType, picUrl, uid)
    }


}