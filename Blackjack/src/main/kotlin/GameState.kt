package org.nymph

import kotlinx.serialization.Serializable

@Serializable
enum class GameState {
    Closure,
    CanRegister,
    Started
}