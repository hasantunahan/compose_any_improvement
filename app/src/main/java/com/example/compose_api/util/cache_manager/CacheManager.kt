package com.example.compose_api.util.cache_manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ICacheManager {

    override fun getString(key: String): Flow<String> {
        val prefKey = stringPreferencesKey(key);
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preference ->
                preference[prefKey] ?: ""
            }
    }

    override suspend fun setString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key);
        dataStore.edit { preference ->
            preference[prefKey] = value
        }
    }


}

