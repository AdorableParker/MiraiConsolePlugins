package org.nymph

class CorpusData(rs: Map<String, Any>) {
    val id: Int by rs
    val answer: String by rs
    val question: String by rs
    val fromGroup:Long by rs
}

