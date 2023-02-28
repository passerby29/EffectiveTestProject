package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.LatestItems
import dev.passerby.effectivetestproject.domain.repos.MainRepository
import retrofit2.Response
@Suppress("unused")
class GetLatestListUseCase(private val repository: MainRepository) {
    suspend fun getLatestList(): Response<LatestItems>? {
        return repository.getLatestItemsList()
    }
}