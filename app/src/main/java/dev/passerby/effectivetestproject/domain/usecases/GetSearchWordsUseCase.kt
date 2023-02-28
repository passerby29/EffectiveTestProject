package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.repos.MainRepository
import retrofit2.Response

class GetSearchWordsUseCase(private val repository: MainRepository) {
    suspend fun getSearchWords(): SearchWords {
        return repository.getSearchWords()
    }
}