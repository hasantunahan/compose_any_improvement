package com.example.compose_api.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.compose_api.constant.ApplicationConstant
import com.example.compose_api.service.ReqresApi
import com.example.compose_api.service.repository.ReqresRepository
import com.example.compose_api.util.cache_manager.CacheManager
import com.example.compose_api.util.cache_manager.ICacheManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideReqresApi(): ReqresApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApplicationConstant.baseUrl).build().create(ReqresApi::class.java)
    }

    @Singleton
    @Provides
    fun providerReqresRepository(api: ReqresApi) = ReqresRepository(api)


    @InstallIn(SingletonComponent::class)
    @Module
    object DataStoreModule {

        @Provides
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
                produceFile = { context.preferencesDataStoreFile("_application_store") })
        }
    }

    @InstallIn(ViewModelComponent::class)
    @Module
    abstract class UserPreferenceModule {
        @Binds
        abstract fun bindUserPreferences(impl: CacheManager): ICacheManager
    }
}