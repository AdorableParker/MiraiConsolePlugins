package org.nymph

import kotlinx.serialization.Serializable


@Serializable
data class UserAccount(
    var gold: Int,
    var cube: Int,
    var favorAbility: Int,
    var loginTime: Long
)