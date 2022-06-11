package ru.eldarsbackend.features.games

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.eldarsbackend.features.login.LoginReceiveRemote

fun Application.configureGamesRouting() {

    routing {
        post ("/games/search") {
            val gamesController = GamesController(call)
            gamesController.performSearch()
        }

        post ("/games/create"){
            val gamesController = GamesController(call)
            gamesController.createGame()
        }
    }
}
