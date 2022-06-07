package ru.eldarsbackend.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.eldarsbackend.features.login.LoginReceiveRemote

fun Application.configureRegisterRouting() {

    routing {
        get("/register") {
            val receive = call.receive<RegisterReceiveRemote>()
            if (!receive.email.isValidEmail()){
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            }
            call.respondText("Hello World!")
        }
    }
}

fun String.isValidEmail(): Boolean{
    return true
}