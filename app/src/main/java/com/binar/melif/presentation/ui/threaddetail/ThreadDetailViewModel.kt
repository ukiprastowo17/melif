package com.binar.melif.presentation.ui.threaddetail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.firebase.model.SubThreadItem
import com.binar.melif.data.firebase.model.ThreadItem
import com.binar.melif.data.repository.ThreadRepository
import com.binar.melif.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ThreadDetailViewModel(
    private val threadRepository: ThreadRepository,
    private val userRepository: UserRepository,
    private val intentData: Bundle
) : ViewModel() {

    val parentThread: ThreadItem? by lazy {
        intentData.getParcelable(ThreadDetailActivity.EXTRAS_PARENT_THREAD)
    }

    val replyThreadResult = MutableLiveData<Resource<Boolean>>()

    fun replyThread(content: String) {
        replyThreadResult.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            replyThreadResult.postValue(
                threadRepository.createSubThread(
                    parentThread?.id.orEmpty(),
                    generateSubThread(content)
                )
            )
        }
    }

    private fun generateSubThread(content: String): SubThreadItem {
        return SubThreadItem(content = content, creator = userRepository.getCurrentUser())
    }

    fun getCurrentUser() = userRepository.getCurrentUser()

    fun getSubThread() = threadRepository.getSubThread(parentThread?.id.orEmpty())
}