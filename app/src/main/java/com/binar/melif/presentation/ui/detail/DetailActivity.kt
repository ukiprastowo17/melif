package com.binar.melif.presentation.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import coil.load
import com.binar.melif.BuildConfig
import com.binar.melif.R
import com.binar.melif.base.BaseViewModelActivity
import com.binar.melif.base.wrapper.Resource
import com.binar.melif.data.network.api.model.MovieDetail
import com.binar.melif.data.network.api.model.TvShowDetail
import com.binar.melif.databinding.ActivityDetailBinding
import com.google.android.material.appbar.AppBarLayout
import org.koin.core.parameter.parametersOf

class DetailActivity : BaseViewModelActivity<ActivityDetailBinding, DetailViewModel>(
    ActivityDetailBinding::inflate
) {

    override val viewModel: DetailViewModel by viewModel {
        parametersOf(intent.extras ?: bundleOf())
    }

    companion object {
        const val TITLE = "title"
        const val EXTRA_TYPE = "EXTRA_TYPE"
        fun startActivity(context: Context, animeId: String) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_TYPE, animeId)
            })
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        with(binding) {
            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                if (collapseLayout.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                        collapseLayout
                    )
                ) {
                    toolbar.apply {
                        setBackgroundColor(Color.WHITE)
                        title = intent.getStringExtra(TITLE)
                        visibility = View.VISIBLE
                        setNavigationIcon(R.drawable.ic_back)
                    }
                    collapseLayout.setCollapsedTitleTextColor(Color.BLACK)
                } else {
                    toolbar.apply {
                        setBackgroundColor(Color.TRANSPARENT)
                        toolbar.visibility = View.VISIBLE
                        setNavigationIcon(R.drawable.ic_back)
                    }
                    collapseLayout.setExpandedTitleColor(Color.TRANSPARENT)
                }
            })
        }
    }

    override fun initView() {
        super.initView()
       // viewModel.fetchDetailTvShow("1399")
        viewModel.fetchDetailMovie("505642")

    }

    override fun observeData() {
        super.observeData()
        viewModel.detailResultTvShow.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    //do nothing
                }
                is Resource.Error -> {
                    showError()
                    showErrorMessage(it.exception?.message.orEmpty())
                }
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    it.payload?.let { detailTvShow ->
                        showDataTvShow(detailTvShow)
                    }
                }
            }
        }
        viewModel.detailResultMovie.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    //do nothing
                }
                is Resource.Error -> {
                    showError()
                    showErrorMessage(it.exception?.message.orEmpty())
                }
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    it.payload?.let { detailMovie ->
                        showDataMovie(detailMovie)
                    }
                }
            }
        }
    }

    private fun showError() {
        with(binding) {
            progressBarDetail.isVisible = false
            tvErrorDetail.isVisible = true
            nestedScrollView.isVisible = false
        }
    }

    private fun showLoading() {
        with(binding) {
            progressBarDetail.isVisible = true
            tvErrorDetail.isVisible = false
            nestedScrollView.isVisible = false
        }
    }

    private fun showErrorMessage(msg: String) {
        binding.tvErrorDetail.text = msg
    }

    private fun showDataTvShow(dataTvShow: TvShowDetail) {
        with(binding) {
            progressBarDetail.isVisible = false
            tvErrorDetail.isVisible = false
            nestedScrollView.isVisible = true
            bindDataTvShow(dataTvShow)
        }
    }
    private fun showDataMovie(dataMovie: MovieDetail) {
        with(binding) {
            progressBarDetail.isVisible = false
            tvErrorDetail.isVisible = false
            nestedScrollView.isVisible = true
            bindDataMovie(dataMovie)
        }
    }

    private fun bindDataTvShow(dataTvShow: TvShowDetail) {
        title = dataTvShow.name
        with(binding) {
            ivHeaderDetail.load(BuildConfig.BASE_POSTER_IMG_URL + dataTvShow.backdropPath)
            imgPosterDetail.load(BuildConfig.BASE_POSTER_IMG_URL + dataTvShow.posterPath)
            tvTitleDetail.text = dataTvShow.name
            tvRateDetail.text = dataTvShow.voteAverage.toString()
            tvReleaseDetail.text = dataTvShow.firstAirDate
            tvDescDetail.text = dataTvShow.overview
            collapseLayout.title = dataTvShow.name
            tvGenreDetail.text = ""
            dataTvShow.genres.forEachIndexed { index, genre ->
                val result = if (index == dataTvShow.genres.size - 1) {
                    genre.name
                } else {
                    "${genre.name}, "
                }
                tvGenreDetail.append(result)
            }
        }
    }
    private fun bindDataMovie(dataMovie: MovieDetail) {
        title = dataMovie.originalTitle
        with(binding) {
            ivHeaderDetail.load(BuildConfig.BASE_POSTER_IMG_URL + dataMovie.backdropPath)
            imgPosterDetail.load(BuildConfig.BASE_POSTER_IMG_URL + dataMovie.posterPath)
            tvTitleDetail.text = dataMovie.originalTitle
            tvRateDetail.text = dataMovie.voteAverage.toString()
            tvReleaseDetail.text = dataMovie.releaseDate
            tvDescDetail.text = dataMovie.overview
            collapseLayout.title = dataMovie.originalTitle
            tvGenreDetail.text = ""
            dataMovie.genres.forEachIndexed { index, genre ->
                val result = if (index == dataMovie.genres.size - 1) {
                    genre.name
                } else {
                    "${genre.name}, "
                }
                tvGenreDetail.append(result)
            }
        }
    }
}