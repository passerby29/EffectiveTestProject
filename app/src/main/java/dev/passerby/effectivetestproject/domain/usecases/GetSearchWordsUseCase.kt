package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.repos.MainRepository
import retrofit2.Response
@Suppress("unused")
class GetSearchWordsUseCase(private val repository: MainRepository) {
    suspend fun getSearchWords(): Response<SearchWords>? {
        return repository.getSearchWords()
    }
}