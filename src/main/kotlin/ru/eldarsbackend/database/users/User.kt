package ru.eldarsbackend.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


object User: Table("users") {
    private var login = User.varchar("login", 25)
    private var password = User.varchar("password", 25)
    private var username = User.varchar("username", 30)
    private var email = User.varchar("email", 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            User.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email ?: ""
            }
        }
    }

    fun fetchUser(login: String): UserDTO {
        val userModel = User.select { User.login.eq(login) }.single()
        return UserDTO(
            login = userModel[User.login],
            password = userModel[password],
            username = userModel[username],
            email = userModel[email]
        )
    }
}