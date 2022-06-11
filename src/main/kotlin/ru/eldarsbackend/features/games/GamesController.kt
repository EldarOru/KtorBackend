package ru.eldarsbackend.features.games

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.eldarsbackend.database.games.Games
import ru.eldarsbackend.database.games.mapToCreateGameResponse
import ru.eldarsbackend.database.games.mapToGameDTO
import ru.eldarsbackend.features.games.models.CreateGameRequest
import ru.eldarsbackend.features.games.models.FetchGameRequest
import ru.eldarsbackend.utils.TokenCheck

class GamesController(private val call: ApplicationCall) {

    suspend fun performSearch() {
        val request = call.receive<FetchGameRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())) {
            call.respond(Games.fetchGames().filter { it.name.contains(request.searchQuery, ignoreCase = true) })
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun createGame() {
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateGameRequest>()
            val game = request.mapToGameDTO()
            Games.insert(game)
            call.respond(game.mapToCreateGameResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }
}