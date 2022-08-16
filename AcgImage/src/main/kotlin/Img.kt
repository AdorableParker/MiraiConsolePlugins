package org.nymph

import kotlinx.serialization.Serializable

@Serializable
data class Img(
    val pid: Int,
//    val p:Int,
    val uid:Int,
    val title:String,
    val author: String,
//    val r18: Boolean,
    val width: Int,
    val height: Int,
    val tags: List<String>,
    val urls: Urls
)