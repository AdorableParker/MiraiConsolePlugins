package org.nymph

import kotlinx.serialization.Serializable
import net.mamoe.mirai.contact.ContactList
import net.mamoe.mirai.contact.NormalMember

@Serializable
class GroupData {
    private val pair = mutableListOf<Long>()
    private val solitary = mutableSetOf<Long>()
    private val ntr = mutableSetOf<Long>()
    private val byNtr = mutableSetOf<Long>()


    fun clear() {
        solitary.clear()
        pair.clear()
        ntr.clear()
        byNtr.clear()
    }


    fun inquireState(id: Long): UserInfo {
        return UserInfo(id in ntr, id in byNtr, id in solitary)
    }

    fun doNtr(userID: Long, beau: Long): Long {
        val index = findWife(beau)
        if (index == -1) return -1

        return if (1 == (1..3).random()) {
            val he = pair[index - index % 2 * 2 + 1]
            byNtr.add(he)
            solitary.add(he)
            ntr.add(userID)
            byNtr.remove(userID)
            solitary.remove(userID)
            pair[index - index % 2 * 2 + 1] = userID
            he
        } else 0
    }

    fun getWife(userID: Long, members: ContactList<NormalMember>): Long? {
        val index = findWife(userID)
        if (index != -1) return pair[index - index % 2 * 2 + 1]

        val candidate = mutableListOf<NormalMember>()

//        members.forEach {
//            GroupWife.logger.debug{"${it.id}"}
//        }
//        (solitary + pair).forEach {
//            GroupWife.logger.debug { "$it" }
//        }

        for (it in members.sortedByDescending(NormalMember::lastSpeakTimestamp)) {
            if (it.id !in (solitary + pair)) {
                candidate.add(it)
            }
            if (candidate.size >= 10) break
        }
        return candidate.random().addWife(userID)
    }

    private fun NormalMember.addWife(userID: Long): Long? {
        if (id == userID) {
            solitary.add(id)
            return null
        }
        pair.addAll(setOf(id, userID))
        return id
    }

    fun findWife(userID: Long): Int {
        return pair.indexOf(userID)
    }
}

