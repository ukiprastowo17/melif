package com.binar.melif.di

import com.binar.melif.BuildConfig
import com.binar.melif.auth.AuthViewModel
import com.binar.melif.data.firebase.FirebaseUserAuthDataSourceImpl
import com.binar.melif.data.firebase.UserAuthDataSource
import com.binar.melif.data.repository.UserRepository
import com.binar.melif.data.repository.UserRepositoryImpl
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
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
        single<UserAuthDataSource> { FirebaseUserAuthDataSourceImpl(get()) } // singleton
    }

    private val repository = module {
        single<UserRepository> { UserRepositoryImpl(get()) } // singleton
    }

    private val viewModels = module {
        viewModelOf(::AuthViewModel)
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
    }

}