package com.binar.melif.di

import com.binar.melif.BuildConfig
import com.binar.melif.data.network.api.datasource.MelifApiDataSource
import com.binar.melif.data.network.api.datasource.MelifApiDataSourceImpl
import com.binar.melif.data.network.api.service.MelifApiService
import com.binar.melif.data.repository.Repository
import com.binar.melif.data.repository.RepositoryImpl
import com.binar.melif.presentation.ui.detail.DetailViewModel
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    fun getModules(): List<Module> = listOf(
      networkModule,  common, firebase, viewModels, repository, dataSource
    )

    private val dataSource = module {
        single<MelifApiDataSource> { MelifApiDataSourceImpl(get()) } // singleton
    }
    private val networkModule = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() } // singleton
        single { MelifApiService.invoke(get()) }
    }

    private val repository = module {
        single<Repository> { RepositoryImpl(get()) } // singleton
    }

    private val common = module {
        single { Gson() }
    }

    private val viewModels = module {
        viewModel {  DetailViewModel(get(),it.get()) }
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