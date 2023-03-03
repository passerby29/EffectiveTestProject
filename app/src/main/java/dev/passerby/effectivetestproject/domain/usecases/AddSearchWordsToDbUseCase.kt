package dev.passerby.effectivetestproject.domain.usecases

import dev.passerby.effectivetestproject.data.room.SearchWordsEntity
import dev.passerby.effectivetestproject.domain.repos.MainRepository

class AddSearchWordsToDbUseCase(private val repository: MainRepository) {
    suspend fun addSearchWordsToDB(searchWord: SearchWordsEntity){
        repository.addSearchWordsToDB(searchWord)
    }
}