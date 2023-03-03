package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.data.room.SearchWordsEntity
import dev.passerby.effectivetestproject.domain.repos.MainRepository

class GetSearchWordsFromDbUseCase(private val repository: MainRepository) {
    suspend fun getSearchWordsFromDB(filter: String): List<SearchWordsEntity> {
        return repository.getSearchWordsFromDB(filter)
    }
}