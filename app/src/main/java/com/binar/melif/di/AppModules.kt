package com.binar.melif.di

import com.binar.melif.BuildConfig
import com.binar.melif.data.firebase.*
import com.binar.melif.data.network.api.datasource.*
import com.binar.melif.data.network.api.service.MelifApiService
import com.binar.melif.data.repository.*
import com.binar.melif.presentation.adapter.MovieAdapter
import com.binar.melif.presentation.adapter.TvShowAdapter
import com.binar.melif.presentation.ui.detail.DetailViewModel
import com.binar.melif.presentation.ui.slider.SlideViewModel
import com.binar.melif.presentation.ui.auth.AuthViewModel
import com.binar.melif.presentation.ui.main.MainViewModel
import com.binar.melif.presentation.ui.movie.MovieViewModel
import com.binar.melif.presentation.ui.thread.ThreadViewModel
import com.binar.melif.presentation.ui.threaddetail.ThreadDetailViewModel
import com.binar.melif.presentation.ui.threadform.ThreadFormViewModel
import com.binar.melif.presentation.ui.tvshow.TvShowViewModel
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    fun getModules(): List<Module> = listOf(
        networkModule,  common, firebase, viewModels, repository, dataSource, adapter
    )


    private val networkModule = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() } // singleton
        single { MelifApiService.invoke(get()) }

    }

    private val dataSource = module {
        single<MelifApiDataSource> { MelifApiDataSourceImpl(get()) } // singleton
        single<UserAuthDataSource> { FirebaseUserAuthDataSourceImpl(get()) } // singleton
        single<TvShowApiDataSource> { TvShowDataSourceImpl(get()) } // singleton
        single<MovieApiDataSource> { MovieDataSourceImpl(get()) } // singleton
        single<ChatDataSource> { FirebaseChatDataSource(get()) } // singleton
    }


    private val repository = module {
        single<Repository> { RepositoryImpl(get()) } // singleton
        single<UserRepository> { UserRepositoryImpl(get()) } // singleton
        single<TvShowRepository> { TvShowRepositoryImpl(get()) } // singleton
        single<MovieRepository> { MovieRepositoryImpl(get()) } // singleton
        single<ChatRepository> { ChatRepositoryImpl(get()) } // singleton
    }


    private val viewModels = module {
        viewModel {   params ->  DetailViewModel(get(),params.get()) }
        viewModelOf(::SlideViewModel)
        viewModelOf(::MainViewModel)
        viewModelOf(::AuthViewModel)
        viewModelOf(::ThreadViewModel)
        viewModelOf(::ThreadFormViewModel)
        viewModel { TvShowViewModel(get()) }
        viewModel { MovieViewModel(get()) }
        viewModel { params -> ThreadDetailViewModel(get(), get(), params.get()) }
    }

    private val adapter = module {
        factory { TvShowAdapter() }
        factory { MovieAdapter() }
    }


    private val common = module {
        single { Gson() }
    }

    private val firebase = module {
        single { FirebaseAuth.getInstance() }
        single { params ->
            GoogleSignIn.getClient(
                params.get(), GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(BuildConfig.FIREBASE_WEB_CLIENT_ID)
                    .requestEmail()
                    .build()
            )
        }
        single { Firebase.database }
    }

}