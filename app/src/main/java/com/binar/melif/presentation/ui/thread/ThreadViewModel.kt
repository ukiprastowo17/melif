package com.binar.melif.presentation.ui.thread

import androidx.lifecycle.ViewModel
import com.binar.melif.data.repository.ThreadRepository

class ThreadViewModel (private val repository: ThreadRepository) : ViewModel(){
    fun getThreadStreamData() = repository.getThread()
}