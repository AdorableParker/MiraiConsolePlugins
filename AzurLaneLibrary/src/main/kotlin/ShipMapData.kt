package org.nymph

class ShipMapData(rs: Map<String, Any>) {
    val originalName: String by rs
    val alias: String by rs
    val rarity: String by rs
    val special: String by rs
    val mapCode: Long by rs
}
