package com.binar.melif.presentation.ui.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.binar.melif.R
import com.binar.melif.base.BaseViewModelActivity
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.local.entity.FavoriteMovieEntity
import com.binar.melif.databinding.ActivityFavBinding
import com.binar.melif.presentation.adapter.HistoryAdapter
import com.binar.melif.presentation.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFavActivity : BaseViewModelActivity<ActivityFavBinding, FavViewModel>(
    ActivityFavBinding::inflate){

    override val viewModel: FavViewModel by viewModel()


    var playersArrList: ArrayList<String>? = null





    private val adapter: HistoryAdapter by lazy {
        HistoryAdapter {
//            val intent = Intent(this@MovieFavActivity, HistoryDetailFormActivity::class.java)
//            intent.putExtra("name_result", it.name_result.toString())
//            startActivity(intent)
//            Toast.makeText(this@MovieFavActivity, "Go To About Activity", Toast.LENGTH_SHORT)
//                .show()
        }
    }



    override fun onStart() {
        super.onStart()
        viewModel.getAllResult()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        observeData()
        playersArrList = ArrayList()
        initRecyclerView()
        initData()




    }

    private fun initData() {

        viewModel.getAllResult()
    }



     override fun observeData() {
         super.observeData()
        viewModel.initialDataResultHistory.observe(this) {
            when (it) {
                is Resource.Empty -> {
//                    showEmptyData()
                }

                is Resource.Error -> {
//                    showError()
//                    setErrorMessage(it.exception?.message.orEmpty())
                }
                is Resource.Loading -> showLoading()
                is Resource.Success -> showData(it.payload)
            }
        }


    }
















    private fun showData(data: List<FavoriteMovieEntity>?) {

        data?.let { listData ->
            binding.pbForm.isVisible = false
            binding.tvError.isVisible = false
            binding.rvNotes.isVisible = true
            if (listData.isNotEmpty()) {
                adapter.setItems(listData)



            } else {
//                showEmptyData()
            }
        }

    }



    private fun showLoading() {
        binding.pbForm.isVisible = true
        binding.tvError.isVisible = false
        binding.rvNotes.isVisible = false
    }

    private fun showError(message: String?) {
        binding.pbForm.isVisible = false
        binding.tvError.isVisible = true
        binding.rvNotes.isVisible = false
        message?.let {
            binding.tvError.text = it
        }
    }

    private fun initRecyclerView() {
        binding.rvNotes.adapter = this@MovieFavActivity.adapter
    }


}


