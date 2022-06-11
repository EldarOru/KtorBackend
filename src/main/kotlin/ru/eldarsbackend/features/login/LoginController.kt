package ru.eldarsbackend.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.eldarsbackend.database.tokens.TokenDTO
import ru.eldarsbackend.database.tokens.Tokens
import ru.eldarsbackend.database.users.Users
import ru.eldarsbackend.features.register.RegisterResponseRemote
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = receive.login,
                    token = token
                    )
                )
                call.respond(RegisterResponseRemote(token = token))
            }
        }
    }
}