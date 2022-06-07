package ru.eldarsbackend.features.register

import ru.eldarsbackend.database.users.User

class RegisterController {

    fun registerNewUser(registerReceiveRemote: RegisterReceiveRemote) {
        val isUserExist = User.fetchUser(registerReceiveRemote.login)
    }
}