package dev.passerby.effectivetestproject.domain.repos

import dev.passerby.effectivetestproject.domain.models.User

interface LoginRepository {

    fun addUser(user: User)

    fun getUser(first_name: String): User
}