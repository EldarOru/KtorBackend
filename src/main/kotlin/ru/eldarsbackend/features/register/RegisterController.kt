package ru.eldarsbackend.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.eldarsbackend.database.tokens.TokenDTO
import ru.eldarsbackend.database.tokens.Tokens
import ru.eldarsbackend.database.users.UserDTO
import ru.eldarsbackend.database.users.Users
import java.util.*

class RegisterController(private val call : ApplicationCall) {

    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()){
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }
        call.respondText("Hello World!")

        val userDTO = Users.fetchUser(registerReceiveRemote.login)
        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
          val token = UUID.randomUUID().toString()
            try {
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            Tokens.insert(TokenDTO(
                id = UUID.randomUUID().toString(),
                login = registerReceiveRemote.login,
                token = token
            ))

            call.respond(RegisterResponseRemote(token = token))
        }
    }
}