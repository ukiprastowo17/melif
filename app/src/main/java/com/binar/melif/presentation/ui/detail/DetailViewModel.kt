package com.binar.melif.presentation.ui.detail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.network.api.model.*
import com.binar.melif.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository, val intentData: Bundle) : ViewModel() {

    val detailResultTvShow = MutableLiveData<Resource<TvShowDetail>>()
    val detailResultMovie = MutableLiveData<Resource<MovieDetail>>()
    private val videoMelif = MutableLiveData<Resource<MelifVideo>>()
    val videos: MutableLiveData<Resource<MelifVideo>> = videoMelif

    fun fetchDetailTvShow(tvId: String) {
        //val tvId = intentData.getString(DetailActivity.EXTRA_TYPE)
        tvId.let {
            viewModelScope.launch(Dispatchers.IO) {
                detailResultTvShow.postValue(Resource.Loading())
                detailResultTvShow.postValue(repository.getDetailTvShow(tvId))
            }
        }
    }

    fun fetchDetailMovie(movieId: String) {
        //val movieId = intentData.getString(DetailActivity.EXTRA_TYPE)
        movieId.let {
            viewModelScope.launch(Dispatchers.IO) {
                detailResultMovie.postValue(Resource.Loading())
                detailResultMovie.postValue(repository.getDetailMovie(movieId))
            }
        }
    }

    fun fetchVideoMovie(movieVideoId: String) {
        movieVideoId.let {
            viewModelScope.launch(Dispatchers.IO) {
                videoMelif.postValue(Resource.Loading())
                videoMelif.postValue(repository.getVideoMovie(movieVideoId))
            }
        }
    }

    fun fetchVideoTvShow(tvShowVideoId: String) {
        tvShowVideoId.let {
            viewModelScope.launch(Dispatchers.IO) {
                videoMelif.postValue(Resource.Loading())
                videoMelif.postValue(repository.getVideoTvShow(tvShowVideoId))

            }
        }
    }
}