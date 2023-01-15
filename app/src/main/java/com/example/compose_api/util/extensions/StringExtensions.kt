package com.example.compose_api.util.extensions

import kotlinx.coroutines.flow.Flow

fun Flow<String>?.safeToString() : String{
    return this?.toString() ?: ""
}