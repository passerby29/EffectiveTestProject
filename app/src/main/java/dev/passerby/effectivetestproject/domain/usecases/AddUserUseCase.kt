package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.User
import dev.passerby.effectivetestproject.domain.repos.LoginRepository
@Suppress("unused")
class AddUserUseCase(private val repository: LoginRepository) {
    suspend fun addUser(user: User){
        repository.addUser(user)
    }
}