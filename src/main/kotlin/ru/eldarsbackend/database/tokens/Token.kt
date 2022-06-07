package ru.eldarsbackend.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


object Token: Table("tokens") {
    private var id = Token.varchar("id", 50)
    private var login = Token.varchar("login", 25)
    private var token = Token.varchar("token", 25)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Token.insert {
                it[id] = tokenDTO.id
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }
}