package dev.passerby.effectivetestproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.effectivetestproject.data.impls.MainRepositoryImpl
import dev.passerby.effectivetestproject.data.room.SearchWordsEntity
import dev.passerby.effectivetestproject.data.server.BaseResponse
import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.usecases.AddSearchWordsToDbUseCase
import dev.passerby.effectivetestproject.domain.usecases.GetSearchWordsFromDbUseCase
import dev.passerby.effectivetestproject.domain.usecases.GetSearchWordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeAViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getSearchWordsUseCase = GetSearchWordsUseCase(repository)
    private val addSearchWordToDbUseCase = AddSearchWordsToDbUseCase(repository)
    private val getSearchWordsFromDbUseCase = GetSearchWordsFromDbUseCase(repository)

    var result: MutableLiveData<BaseResponse<SearchWords>> = MutableLiveData()

    fun getSearchWords() {
        result.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = getSearchWordsUseCase.getSearchWords()
                if (response?.code() == 200) {
                    result.value = BaseResponse.Success(response.body())
                } else {
                    result.value = BaseResponse.Error(response?.message())
                }
            } catch (ex: Exception) {
                result.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun addSearchWord(searchWord: SearchWordsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addSearchWordToDbUseCase.addSearchWordsToDB(searchWord)
        }
    }

    fun getSearchWordsFromDG(filter: String): List<SearchWordsEntity> {
        var list: List<SearchWordsEntity> = emptyList()
        viewModelScope.launch(Dispatchers.IO) {
             list = getSearchWordsFromDbUseCase.getSearchWordsFromDB(filter)
        }
        return list
    }
}