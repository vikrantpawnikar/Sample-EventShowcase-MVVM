package com.vikrant.core.retrofitclient

sealed class ResultState<T> {
    data class Progress<T>(val data: T? = null) : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Failure<T>(val e: Throwable) : ResultState<T>()
}