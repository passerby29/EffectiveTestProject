package dev.passerby.effectivetestproject.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.effectivetestproject.data.impls.LoginRepositoryImpl
import dev.passerby.effectivetestproject.domain.models.User
import dev.passerby.effectivetestproject.domain.usecases.AddUserUseCase
import dev.passerby.effectivetestproject.domain.usecases.CheckIsUserExistsUseCase
import dev.passerby.effectivetestproject.presentation.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("unused")
class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LoginRepositoryImpl(application)

    private val addUserUseCase = AddUserUseCase(repository)
    private val checkIsUserExistsUseCase = CheckIsUserExistsUseCase(repository)

    private val utils = Utils()

    private val _errorInputFirstName = MutableLiveData<Boolean>()
    val errorInputFirstName: LiveData<Boolean>
        get() = _errorInputFirstName

    private val _errorInputLastName = MutableLiveData<Boolean>()
    val errorInputLastName: LiveData<Boolean>
        get() = _errorInputLastName

    private val _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean>
        get() = _errorInputEmail

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun addUser(inputFirstName: String?, inputLastName: String?, inputEmail: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            val firstName = utils.parseInput(inputFirstName)
            val lastName = utils.parseInput(inputLastName)
            val email = utils.parseInput(inputEmail)
            val fieldsValid = validateInput(firstName, lastName, email)
            if (fieldsValid) {
                val list = checkUserIsExists(email)
                if (list.isNotEmpty()) {
                    Log.d("SignInViewModel", "addUser: Error")
                } else {
                    val user = User(firstName, lastName, email)
                    addUserUseCase.addUser(user)
                    Log.d("SignInViewModel", "addUser: Success")
                    finishWork()
                }
            }
        }

    private suspend fun checkUserIsExists(email: String): List<User> {
        return checkIsUserExistsUseCase.checkUserIsExists(email)
    }

    private fun validateInput(
        inputFirstName: String,
        inputLastName: String,
        inputEmail: String
    ): Boolean {
        var result = true
        if (inputFirstName.isBlank()) {
            _errorInputFirstName.postValue(true)
            result = false
        }
        if (inputLastName.isBlank()) {
            _errorInputLastName.postValue(true)
            result = false
        }
        if (inputEmail.isBlank()) {
            _errorInputEmail.postValue(true)
            result = false
        }
        return result
    }

    fun resetErrorInputFirstName() {
        _errorInputFirstName.value = false
    }

    fun resetErrorInputLastName() {
        _errorInputLastName.value = false
    }

    fun resetErrorInputEmail() {
        _errorInputEmail.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.postValue(Unit)
    }
}