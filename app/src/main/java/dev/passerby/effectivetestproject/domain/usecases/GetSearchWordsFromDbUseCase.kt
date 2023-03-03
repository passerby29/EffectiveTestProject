package dev.passerby.effectivetestproject.domain.usecases

import androidx.lifecycle.LiveData
import dev.passerby.effectivetestproject.data.room.SearchWordsEntity
import dev.passerby.effectivetestproject.domain.repos.MainRepository

class GetSearchWordsFromDbUseCase(private val repository: MainRepository) {
    fun getSearchWordsFromDB(filter: String): LiveData<List<SearchWordsEntity>> {
        return repository.getSearchWordsFromDB(filter)
    }
}