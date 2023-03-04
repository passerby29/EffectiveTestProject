package dev.passerby.effectivetestproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.effectivetestproject.data.impls.MainRepositoryImpl
import dev.passerby.effectivetestproject.data.room.SearchWordsEntity
import dev.passerby.effectivetestproject.data.server.BaseResponse
import dev.passerby.effectivetestproject.domain.models.FlashSaleItems
import dev.passerby.effectivetestproject.domain.models.LatestItems
import dev.passerby.effectivetestproject.domain.models.SearchWords
import dev.passerby.effectivetestproject.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeAViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getSearchWordsUseCase = GetSearchWordsUseCase(repository)
    private val addSearchWordToDbUseCase = AddSearchWordsToDbUseCase(repository)
    private val getSearchWordsFromDbUseCase = GetSearchWordsFromDbUseCase(repository)
    private val getLatestListUseCase = GetLatestListUseCase(repository)
    private val getFlashSaleListUseCase = GetFlashSaleListUseCase(repository)

    var searchWordsResult: MutableLiveData<BaseResponse<SearchWords>?> = MutableLiveData()
    var latestListResult: MutableLiveData<BaseResponse<LatestItems>?> = MutableLiveData()
    var flashSaleListResult: MutableLiveData<BaseResponse<FlashSaleItems>?> = MutableLiveData()

    fun getSearchWords() {
        viewModelScope.launch {
            val response = getSearchWordsUseCase.getSearchWords()
            getData(response, searchWordsResult)
        }
    }

    fun getLatestList() {
        viewModelScope.launch {
            val response = getLatestListUseCase.getLatestList()
            getData(response, latestListResult)
        }
    }

    fun getFlashSaleList() {
        viewModelScope.launch {
            val response = getFlashSaleListUseCase.getFlashSaleList()
            getData(response, flashSaleListResult)
        }
    }


    fun addSearchWord(searchWord: SearchWordsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addSearchWordToDbUseCase.addSearchWordsToDB(searchWord)
        }
    }

    fun getSearchWordsFromDB(filter: String): LiveData<List<SearchWordsEntity>> {
        return getSearchWordsFromDbUseCase.getSearchWordsFromDB(filter)
    }

    private fun <T> getData(response: Response<T>?, result: MutableLiveData<BaseResponse<T>?>) {
        result.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
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