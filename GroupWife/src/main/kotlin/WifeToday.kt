package org.nymph

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.MemberCommandSenderOnMessage
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.getMember
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.nymph.GroupWifeData.cleanList
import org.nymph.GroupWifeData.groupWifeUpdate
import org.nymph.GroupWifeData.wifeGroupMap
import java.net.URL
import java.time.LocalDateTime

object WifeToday : SimpleCommand(
    GroupWife, "WifeToday", "今日老婆",
    description = "每天一个群老婆"
) {
    override val usage: String = "${CommandManager.commandPrefix}今日老婆 [ntr目标]\t#$description"

    @Handler
    suspend fun MemberCommandSenderOnMessage.main() {
        if (group.botMuteRemaining > 0) return

        if (LocalDateTime.now().dayOfYear != groupWifeUpdate) cleanList(LocalDateTime.now().dayOfYear)

        val groupData = wifeGroupMap.getOrPut(group.id) { GroupData() }
        val userInfo = groupData.inquireState(user.id)

        if (userInfo.bullied) {
            sendMessage("你醒啦,你的老婆被骗走了哦")
            return
        }
        if (userInfo.single) {
            sendMessage("今天你没有老婆哒,快去牛一个吧")
            return
        }

        sendMessage(getChain(group, groupData.getWife(user.id, group.members)))
    }


    @Handler
    suspend fun MemberCommandSenderOnMessage.main(beau: Member) {
        if (group.botMuteRemaining > 0) return

        if (beau.id == user.id) {
            sendMessage("你娶你自己?")
            return
        }
        if (beau.id == bot.id) {
            sendMessage("笨蛋！不准娶我！哼唧！")
            return
        }
        if (LocalDateTime.now().dayOfYear != groupWifeUpdate) {
            cleanList(LocalDateTime.now().dayOfYear)
            sendMessage("今天还没人有老婆")
            return
        }

        val groupData = wifeGroupMap.getOrPut(group.id) { GroupData() }
        if (groupData.findWife(user.id) != -1) {
            sendMessage("喂,你家里还有个吃白饭的呢")
            return
        }

        val userInfo = groupData.inquireState(user.id)

        if (userInfo.bully) {
            sendMessage("今天你已经牛过了")
            return
        }

        if (!userInfo.single) {
            sendMessage(getChain(group, groupData.getWife(user.id, group.members)))
            return
        }
        when (val victim = groupData.doNtr(user.id, beau.id)) {
            -1L -> sendMessage("你无法牛一个没有老婆的人")
            0L -> sendMessage(getChain(group, beau, null))
            else -> sendMessage(getChain(group, beau, victim))
        }
    }

    private suspend fun getChain(group: Group, beau: Member, victim: Long?): Message {
        return if (victim != null) buildMessageChain {
            +"你成功的骗走了${group.getMember(victim)?.nameCardOrNick}的老婆"
            +(withContext(Dispatchers.IO) {
                URL(beau.avatarUrl).openConnection().getInputStream()
            }).uploadAsImage(group)
            +"${beau.nameCardOrNick}(${beau.id}),Ta现在是你的老婆了"
        }
        else PlainText("你试图把${beau.nameCardOrNick}(${beau.id})骗做老婆,但是失败了,你还是没有老婆")
    }

    private suspend fun getChain(group: Group, wifeID: Long?): Message {
        if (wifeID == null) return PlainText("你今天没有老婆哒")

        val chain = MessageChainBuilder()
        val wife = group.getMember(wifeID)

        chain.add("今天你的群老婆是")
        chain.add(
            (withContext(Dispatchers.IO) {
                URL(wife?.avatarUrl).openConnection().getInputStream()
            }).uploadAsImage(group)
        )
        chain.add("${wife?.nameCardOrNick}($wifeID)哒")
        return chain.build()
    }
}