package com.binar.melif.di

import android.content.Context
import com.binar.melif.data.pref.Preference
import com.binar.melif.data.pref.PreferenceDataSource
import com.binar.melif.data.pref.PreferenceDataSourceImpl


object ServiceLocator {

    fun provideUserPreference(context: Context): Preference {
        return Preference(context)
    }



    fun providePreferenceDataSource(context: Context): PreferenceDataSource {
        return PreferenceDataSourceImpl(provideUserPreference(context))
    }




}