package com.binar.melif.presentation.ui.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.melif.data.firebase.model.User
import com.binar.melif.data.repository.UserRepository


class SplashViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    val currentUserLiveData = MutableLiveData<User?>()

    fun getCurrentUser() {
        currentUserLiveData.postValue(userRepository.getCurrentUser())
    }
}