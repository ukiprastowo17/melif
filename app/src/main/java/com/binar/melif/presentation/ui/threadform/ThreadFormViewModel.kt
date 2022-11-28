package com.binar.melif.presentation.ui.threadform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.firebase.model.ThreadItem
import com.binar.melif.data.repository.ThreadRepository
import com.binar.melif.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ThreadFormViewModel(
    private val threadRepository: ThreadRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val createThreadResult = MutableLiveData<Resource<Boolean>>()

    fun createThread(title: String, content: String) {
        createThreadResult.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            createThreadResult.postValue(threadRepository.createThread(generateThreadItem(title,content)))
        }
    }

    private fun generateThreadItem(title: String, content: String) : ThreadItem {
        return ThreadItem(
            creator = userRepository.getCurrentUser(),
            title = title,
            content = content
        )
    }

}