package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.User
import dev.passerby.effectivetestproject.domain.repos.LoginRepository

@Suppress("unused")
class GetUserUseCase(private val repository: LoginRepository) {
    suspend fun getUser(first_name: String): User {
        return repository.getUser(first_name)
    }
}