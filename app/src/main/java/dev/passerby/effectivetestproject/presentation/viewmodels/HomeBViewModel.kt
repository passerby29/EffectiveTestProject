package dev.passerby.effectivetestproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.*
import dev.passerby.effectivetestproject.data.impls.MainRepositoryImpl
import dev.passerby.effectivetestproject.data.server.BaseResponse
import dev.passerby.effectivetestproject.domain.models.SelectedItem
import dev.passerby.effectivetestproject.domain.usecases.GetSelectedItemUseCase
import kotlinx.coroutines.launch

class HomeBViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)

    private val getSelectedItemUseCase = GetSelectedItemUseCase(repository)

    var result: MutableLiveData<BaseResponse<SelectedItem>?> = MutableLiveData()

    fun getSelectedItem() {
        result.value = BaseResponse.Loading()
        viewModelScope.launch {
            val response = getSelectedItemUseCase.getSelectedItem()

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