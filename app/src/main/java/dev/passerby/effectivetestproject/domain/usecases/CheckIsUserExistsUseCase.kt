package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.User
import dev.passerby.effectivetestproject.domain.repos.LoginRepository

@Suppress("unused")
class CheckIsUserExistsUseCase(private val repository: LoginRepository) {
    suspend fun checkUserIsExists(email: String): List<User> {
        return repository.checkUserIsExists(email)
    }
}