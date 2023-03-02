package dev.passerby.effectivetestproject.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.effectivetestproject.data.impls.LoginRepositoryImpl
import dev.passerby.effectivetestproject.domain.usecases.GetUserUseCase
import dev.passerby.effectivetestproject.presentation.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LoginRepositoryImpl(application)

    private val getUserUseCase = GetUserUseCase(repository)

    private val utils = Utils()

    private val _errorInputFirstName = MutableLiveData<Boolean>()
    val errorInputFirstName: LiveData<Boolean>
        get() = _errorInputFirstName

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getUser(inputFirstName: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val firstName = utils.parseInput(inputFirstName)
            val fieldsValid = validateInput(firstName)
            if (fieldsValid) {
                val list = getUserUseCase.getUser(firstName)
                if (list.isEmpty()) {
                    invalidUser()
                } else {
                    Log.d("LoginViewModel", "getUser: Success")
                    finishWork()
                }
            }
        }
    }

    private fun invalidUser() {
        _errorInputFirstName.postValue(true)
    }

    private fun validateInput(inputFirstName: String): Boolean {
        var result = true
        if (inputFirstName.isBlank()) {
            _errorInputFirstName.postValue(true)
            result = false
        }
        return result
    }

    fun resetErrorInputFirstName() {
        _errorInputFirstName.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.postValue(Unit)
    }
}