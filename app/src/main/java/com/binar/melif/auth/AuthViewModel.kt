package com.binar.melif.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.firebase.model.User
import com.binar.melif.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class AuthViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    val loginResult = MutableLiveData<Resource<Pair<Boolean, User?>>>()

    fun authenticateGoogleLogin(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginResult.postValue(Resource.Loading())
            loginResult.postValue(userRepository.signInWithCredential(token))
        }
    }
}