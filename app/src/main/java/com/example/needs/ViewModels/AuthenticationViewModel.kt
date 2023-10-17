package com.example.needs.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.needs.Repositories.AuthenticationRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class AuthenticationViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {



    fun signup(email : String, pass : String,callback : (Boolean,String) -> Unit){
        authenticationRepository.signUp(email, pass, callback)


    }

    fun signIn(email : String, pass : String,callback : (Boolean,String) -> Unit){
        authenticationRepository.signIn(email, pass, callback)


    }

    fun signInwithGoogleAccount(account : GoogleSignInAccount, onComplete : (Boolean) -> Unit){
        authenticationRepository.signInWithGoogle(account,onComplete)
    }



}