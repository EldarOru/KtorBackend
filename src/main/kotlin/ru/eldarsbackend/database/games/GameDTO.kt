package ru.eldarsbackend.database.games

import ru.eldarsbackend.features.games.models.CreateGameRequest
import ru.eldarsbackend.features.games.models.CreateGameResponse
import java.util.*

data class GameDTO(
    val gameId: String,
    val name: String,
    val description: String,
    val version: String,
    val weight: String
)

fun CreateGameRequest.mapToGameDTO(): GameDTO =
    GameDTO(
        gameId = UUID.randomUUID().toString(),
        name = name,
        description = description,
        version = version,
        weight = weight
    )

fun GameDTO.mapToCreateGameResponse(): CreateGameResponse =
    CreateGameResponse(
        gameID = gameId,
        name = name,
        description = description,
        version = version,
        weight = weight
    )