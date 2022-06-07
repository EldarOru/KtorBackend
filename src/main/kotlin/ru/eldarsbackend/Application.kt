package ru.eldarsbackend

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.eldarsbackend.features.register.configureRegisterRouting
import ru.eldarsbackend.plugins.*

fun main() {
    Database.connect(url = "jdbc:postgresql://localhost:5432/test_db", driver = "org.postgresql.Driver",
    user = "postgres")
    embeddedServer(CIO, port = 8000, host = "0.0.0.0") {
        configureRouting()
        configureRegisterRouting()
        configureSerialization()
    }.start(wait = true)
}
