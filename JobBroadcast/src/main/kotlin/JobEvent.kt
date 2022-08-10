package org.nymph

import kotlinx.serialization.Serializable
import net.mamoe.mirai.event.AbstractEvent

@Serializable
abstract class JobEvent : AbstractEvent() {
    abstract val name: String
    abstract val synopsis: String
    abstract val intervals: Int

    fun addJob(startTime: Int) {
        JobData.timeAxis.getOrPut(startTime) { mutableListOf() }.add(this)
    }
}