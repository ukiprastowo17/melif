package com.binar.melif.di

import com.binar.melif.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    fun getModules(): List<Module> = listOf(
        networkModule,  common, firebase, viewModels, repository, dataSource
    )


    private val dataSource = module {
        single<MelifApiDataSource> { MelifApiDataSourceImpl(get()) } // singleton
        single<UserAuthDataSource> { FirebaseUserAuthDataSourceImpl(get()) }
    }
    private val networkModule = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() } // singleton
        single { MelifApiService.invoke(get()) }
    }

    private val repository = module {
        single<Repository> { RepositoryImpl(get()) } // singleton
        single<UserRepository> { UserRepositoryImpl(get()) }
    }



    private val viewModels = module {
        viewModel {  DetailViewModel(get(),it.get()) }
        viewModelOf(::SplashViewModel)
    }

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