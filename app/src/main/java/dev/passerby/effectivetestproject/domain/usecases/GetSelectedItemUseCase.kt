package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.SelectedItem
import dev.passerby.effectivetestproject.domain.repos.MainRepository
import retrofit2.Response
@Suppress("unused")
class GetSelectedItemUseCase(private val repository: MainRepository) {
    suspend fun getSelectedItem(): Response<SelectedItem>? {
        return repository.getSelectedItem()
    }
}