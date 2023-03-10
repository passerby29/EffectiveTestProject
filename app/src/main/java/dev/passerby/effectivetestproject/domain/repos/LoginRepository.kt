package dev.passerby.effectivetestproject.domain.repos

import dev.passerby.effectivetestproject.domain.models.User

interface LoginRepository {

    suspend fun addUser(user: User)

    suspend fun getUser(first_name: String): List<User>

    suspend fun checkUserIsExists(email: String): List<User>
}