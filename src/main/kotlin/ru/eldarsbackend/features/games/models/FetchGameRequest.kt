package ru.eldarsbackend.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchGameRequest(
    val searchQuery: String
)
