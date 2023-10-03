package com.example.needs.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.needs.Repositories.AuthenticationRepository

class AuthenticationViewModelFactory(private val repository: AuthenticationRepository)  : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(repository) as T
    }
}