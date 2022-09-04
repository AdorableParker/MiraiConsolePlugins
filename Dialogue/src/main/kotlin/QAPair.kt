package org.nymph

import kotlinx.serialization.Serializable

@Serializable
data class QAPair(val question: String, val answer: String)