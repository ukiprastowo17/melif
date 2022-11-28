package com.binar.melif.data.repository

import com.binar.melif.base.BaseRepository
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.firebase.ThreadDataSource
import com.binar.melif.data.firebase.model.SubThreadItem
import com.binar.melif.data.firebase.model.ThreadItem
import com.firebase.ui.database.FirebaseRecyclerOptions

interface ThreadRepository {
    suspend fun createThread(threadItem: ThreadItem): Resource<Boolean>
    suspend fun createSubThread(
        parentThreadId: String,
        subThreadItem: SubThreadItem
    ): Resource<Boolean>
    fun getThread(): FirebaseRecyclerOptions<ThreadItem>
    fun getSubThread(parentThreadId: String): FirebaseRecyclerOptions<SubThreadItem>
}

class ThreadRepositoryImpl(private val dataSource: ThreadDataSource) : BaseRepository(),
    ThreadRepository {
    override suspend fun createThread(threadItem: ThreadItem): Resource<Boolean> {
        return proceed { dataSource.createThread(threadItem) }
    }

    override suspend fun createSubThread(
        parentThreadId: String,
        subThreadItem: SubThreadItem
    ): Resource<Boolean> {
        return proceed { dataSource.createSubThread(parentThreadId, subThreadItem) }
    }

    override fun getThread(): FirebaseRecyclerOptions<ThreadItem> {
        return dataSource.getThread()
    }

    override fun getSubThread(parentThreadId: String): FirebaseRecyclerOptions<SubThreadItem> {
        return dataSource.getSubThread(parentThreadId)
    }
}