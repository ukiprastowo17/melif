package com.binar.melif.presentation.ui.favorite

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.local.entity.FavoriteMovieEntity
import com.binar.melif.data.repository.Repository
import com.binar.melif.presentation.adapter.MovieItem


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class FavViewModel(private val repository: Repository) : ViewModel() {

    val initialDataResultHistory = MutableLiveData<Resource<List<FavoriteMovieEntity>>>()


    fun getAllResult(){
        viewModelScope.launch(Dispatchers.IO){
            initialDataResultHistory.postValue(Resource.Loading())
            delay(1000)
            initialDataResultHistory.postValue(repository.getAllMovieFav())
        }
    }



}


