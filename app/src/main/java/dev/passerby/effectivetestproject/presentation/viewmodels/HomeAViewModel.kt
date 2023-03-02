package dev.passerby.effectivetestproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.effectivetestproject.data.impls.MainRepositoryImpl
import dev.passerby.effectivetestproject.data.server.BaseResponse
import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.usecases.GetSearchWordsUseCase
import kotlinx.coroutines.launch

class HomeAViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl

    private val getSearchWordsUseCase = GetSearchWordsUseCase(repository)

    var result: MutableLiveData<BaseResponse<SearchWords>> = MutableLiveData()

    fun getSearchWords() {
        result.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = repository.getSearchWords()
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
}