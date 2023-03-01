package dev.passerby.effectivetestproject.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.passerby.effectivetestproject.data.impls.LoginRepositoryImpl
import dev.passerby.effectivetestproject.domain.models.User
import dev.passerby.effectivetestproject.domain.usecases.AddUserUseCase
import dev.passerby.effectivetestproject.domain.usecases.GetUserUseCase

@Suppress("unused")
class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LoginRepositoryImpl(application)

    private val getUserUseCase = GetUserUseCase(repository)
    private val addUserUseCase = AddUserUseCase(repository)

    private val _errorInputFirstName = MutableLiveData<Boolean>()
    val errorInputFirstName: LiveData<Boolean>
        get() = _errorInputFirstName

    private val _errorInputLastName = MutableLiveData<Boolean>()
    val errorInputLastName: LiveData<Boolean>
        get() = _errorInputLastName

    private val _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean>
        get() = _errorInputEmail

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    suspend fun getUser(first_name: String?) {
        val firstName = parseInput(first_name)
        if (firstName.isBlank()) {
            _errorInputFirstName.value = true
        } else {
            val user = getUserUseCase.getUser(firstName)
            _user.value = user
        }
    }

    suspend fun addUser(first_name: String?, last_name: String?, _email: String?) {
        val firstName = parseInput(first_name)
        val lastName = parseInput(last_name)
        val email = parseInput(_email)
        val fieldsValid = validateInputLogin(firstName, lastName, email)
        if (fieldsValid) {
            val user = User(firstName, lastName, email)
            addUserUseCase.addUser(user)
            Log.d("SignInViewModel", "addUser: Success")
        }
    }

    private fun parseInput(input: String?): String {
        return input?.trim() ?: ""
    }

    private fun validateInputLogin(first_name: String, last_name: String, _email: String): Boolean {
        var result = true
        if (first_name.isBlank()) {
            _errorInputFirstName.value = true
            result = false
        }
        if (last_name.isBlank()) {
            _errorInputLastName.value = true
            result = false
        }
        if (_email.isBlank()) {
            _errorInputEmail.value = true
            result = false
        }
        return result
    }
}