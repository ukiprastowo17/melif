package com.binar.melif.di

import com.binar.melif.BuildConfig
import com.binar.melif.data.firebase.FirebaseThreadDataSource
import com.binar.melif.data.firebase.ThreadDataSource
import com.binar.melif.data.repository.ThreadRepository
import com.binar.melif.data.repository.ThreadRepositoryImpl
import com.binar.melif.presentation.ui.threaddetail.ThreadDetailViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    fun getModules(): List<Module> = listOf(
         common, firebase, dataSource, repository, viewModels
    )

    private val dataSource = module {
        single<ThreadDataSource> { FirebaseThreadDataSource(get()) } // singleton
    }

    private val repository = module{
        single<ThreadRepository> { ThreadRepositoryImpl(get()) } // singleton
    }

    private val viewModels = module {
        //Cara lain :  viewModelOf(::HomeViewModel)

        viewModel { params -> ThreadDetailViewModel(get(), get(), params.get()) }

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