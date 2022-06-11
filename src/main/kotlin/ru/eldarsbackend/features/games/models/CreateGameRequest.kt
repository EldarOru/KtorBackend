package ru.eldarsbackend.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameRequest(
    val name: String,
    val description: String,
    val version: String,
    val weight: String
)

@Serializable
data class CreateGameResponse(
    val gameID: String,
    val name: String,
    val description: String,
    val version: String,
    val weight: String
)