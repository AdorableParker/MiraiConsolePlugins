package org.nymph

import net.mamoe.mirai.event.AbstractEvent


abstract class JobEvent : AbstractEvent() {
    abstract val name: String
    abstract val synopsis: String
    abstract val intervals: Int

    fun addJob(startTime: Int) {
        JobBroadcast.timeAxis.getOrPut(startTime) { mutableListOf() }.add(this)
    }
}