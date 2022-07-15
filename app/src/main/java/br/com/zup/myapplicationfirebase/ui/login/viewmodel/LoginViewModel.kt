package br.com.zup.myapplicationfirebase.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.myapplicationfirebase.domain.model.AuthRepository
import br.com.zup.myapplicationfirebase.domain.model.User
import br.com.zup.myapplicationfirebase.utils.EMAIL_ERROR_MESSAGE
import br.com.zup.myapplicationfirebase.utils.LOGIN_ERROR_MESSAGE
import br.com.zup.myapplicationfirebase.utils.PASSWORD_ERROR_MESSAGE

class LoginViewModel: ViewModel() {
    private val authRepository = AuthRepository()

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun getCurrentUser() = authRepository.getCurrentUser()

    private fun login(user: User) {
        try {
            authRepository.loginUser(user.email, user.password).addOnSuccessListener {
                _loginState.value = user
            }.addOnFailureListener {
                _errorState.value = LOGIN_ERROR_MESSAGE
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }

    fun validateData(user: User) {
        when {
            user.email.isEmpty() -> {
                _errorState.value = EMAIL_ERROR_MESSAGE
            }
            user.password.isEmpty() -> {
                _errorState.value = PASSWORD_ERROR_MESSAGE
            }
            else -> {
                login(user)
            }
        }
    }
}