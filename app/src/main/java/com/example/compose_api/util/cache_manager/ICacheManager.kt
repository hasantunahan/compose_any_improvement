package com.example.compose_api.util.cache_manager

import kotlinx.coroutines.flow.Flow

interface ICacheManager {
    fun getString(key: String): Flow<String>
    suspend fun setString(key: String, value: String)
}