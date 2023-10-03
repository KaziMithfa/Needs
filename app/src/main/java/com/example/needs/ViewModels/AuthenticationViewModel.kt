package com.example.needs.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.needs.Repositories.AuthenticationRepository

class AuthenticationViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {



    fun signup(email : String, pass : String,callback : (Boolean,String) -> Unit){
        authenticationRepository.signUp(email, pass, callback)


    }

    fun signIn(email : String, pass : String,callback : (Boolean,String) -> Unit){
        authenticationRepository.signIn(email, pass, callback)


    }



}