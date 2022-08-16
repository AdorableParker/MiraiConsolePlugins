package org.nymph

import kotlinx.serialization.Serializable

@Serializable
data class DocData(val error: String, val data: List<Img>)

