package org.nymph

class ConstructTimeData(rs: Map<String, Any>) {
    val originalName: String by rs
    val alias: String by rs
    val time: String by rs
    val limitedTime: Int by rs
}