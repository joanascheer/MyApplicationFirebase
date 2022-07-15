package br.com.zup.myapplicationfirebase.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.myapplicationfirebase.domain.model.AuthRepository

class MainViewModel:ViewModel() {
    private val authRepository = AuthRepository()

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getName() = authRepository.getName()

    fun logout() = authRepository.logout()
}