package ru.eldarsbackend.features.login

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {

    routing {
        get("/login") {
            val login = call.receive(LoginReceiveRemote::class)
            call.respondText("Hello World!")
        }
    }
}