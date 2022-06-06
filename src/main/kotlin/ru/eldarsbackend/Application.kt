package ru.eldarsbackend

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import ru.eldarsbackend.plugins.*

fun main() {
    embeddedServer(CIO, port = 8000, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
