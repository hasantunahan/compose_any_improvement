package com.example.compose_api.util

sealed class Either<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Either<T>(data)
    class Error<T>(message: String, data: T? = null) : Either<T>(data, message)
}