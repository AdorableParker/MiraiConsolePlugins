package org.nymph

import kotlinx.serialization.Serializable
import net.mamoe.mirai.message.data.*

@Serializable
class Game21 {
    private val deck = mutableListOf<String>()

    val playerList = mutableListOf<Player>()

    var gameState = GameState.Closure
    var next: Int = -1

    private fun findPlayer(playerID: Long): Boolean {
        return playerList.find { it.playerID == playerID } != null
    }

    private fun settlement(): Message {
        val info = MessageChainBuilder()
        info.add("本轮结算:\n庄家\t手牌:${playerList[0].handCard.drop(1)}+[暗牌]\n")
        for (index in 1 until playerList.size) {
            val player = playerList[index]
            info.add(At(player.playerID))
            info.add("\n手牌:${player.handCard}\n共计${player.sum()}点")
            when (player.nowPoint) {
                in 1..20 -> info.add(" - 等待\n")
                21 -> info.add(" - 停牌\n")
                else -> info.add(" - 爆牌\n")
            }
        }
        next = nextPlayer()
        if (next != 0) {
            info.add("----------\n轮到")
            info.add(At(playerList[next].playerID))
            info.add("行动")
        } else finalDeal()

        return info.build()
    }

    private fun nextPlayer(): Int {
        for (index in next until playerList.size) {
            if (playerList[index].nowPoint < 21) return index
        }
        return 0
    }

    private fun firstDeal(): Message {
        deck.shuffle()
        gameState = GameState.Processing
        for (index in 0 until (playerList.size * 2)) {
            playerList[index % playerList.size].handCard.add(deck.removeFirst())
        }
        next = 1
        return if (playerList[0].sum() == 21) chipSettlement() else settlement()
    }

    private fun finalDeal(): Message {
        val dealer = playerList[0]
        while (dealer.sum() < 17) dealer.handCard.add(deck.removeFirst())
        return chipSettlement()
    }

    private fun chipSettlement(): Message {

        val (bj, other) = playerList.partition { it.nowPoint == 21 && it.handCard.size == 2 }
        if (bj.find { it.playerID == 0L } != null) {
            bj.forEach { it.odds = 0.0 }
            return PlainText("平局")
        } else bj.forEach { it.odds = 1.5 }

        if (playerList[0].nowPoint <= 21) {
            val (win, loser) = other.partition { it.nowPoint <= 21 && it.nowPoint > playerList[0].nowPoint }
            win.forEach { it.odds = 1.0 }
            val draw = loser.filter { it.nowPoint < playerList[0].nowPoint }
            draw.forEach { it.odds = 0.0 }
        } else {
            val win = other.filter { it.nowPoint <= 21 }
            win.forEach { it.odds = 1.0 }
        }
        val info = MessageChainBuilder()
        for (player in playerList) {
            if (player.playerID == 0L) {
                info.add("庄家:\t牌型:${player.handCard}\n共计${player.nowPoint}点\n")
                continue
            }
            info.add(At(player.playerID))
            info.add("\n牌型:${player.handCard}\n共计${player.sum()}点")
            val account = Account.user.getOrPut(player.playerID) { UserAccount(0, 0, 200, 0) }
            val chips = (player.odds * player.ante).toInt()
            account.gold += chips
            info.add(At(player.playerID))
            info.add("\t筹码结算:$chips\n")
        }
        gameState = GameState.Closure

        return info.build()
    }

    fun init() {
        deck.clear()
        deck.addAll(
            arrayOf(
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
            )
        )
        playerList.add(Player(0))
        gameState = GameState.CanRegister
    }

    fun signUp(playerID: Long): String {
        if (findPlayer(playerID)) return "你已经报过名了"
        if (playerList.size >= 6) return "人数已满,使用发牌命令开始游戏"
        playerList.add(Player(playerID))
        gameState = GameState.CanStarted
        return "报名成功"
    }

    fun signUp(playerID: Long, bet: Int): String {
        if (findPlayer(playerID)) return "你已经报过名了"
        if (playerList.size >= 6) return "人数已满,使用发牌命令开始游戏"
        playerList.add(Player(playerID, ante = bet))
        gameState = GameState.CanStarted
        return "报名成功"
    }

    fun deal(): Message {
        return when (next) {
            -1 -> firstDeal()
            0 -> finalDeal()
            else -> {
                playerList[next].handCard.add(deck.removeFirst())
                settlement()
            }
        }
    }

    fun stop(): Message {
        next++
        next = nextPlayer()
        return if (next != 0)
            buildMessageChain {
                +"轮到"
                +At(playerList[next].playerID)
                +"行动"
            }
        else finalDeal()
    }

    fun shuffle() = deck.shuffle()


}
