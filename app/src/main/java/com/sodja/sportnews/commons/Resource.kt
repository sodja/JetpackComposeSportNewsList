package com.sodja.sportnews.commons

sealed class Resource<out T> {
    object Loading: Resource<Nothing>()

    data class Success<out T>(
        val data: T?
    ): Resource<T>()

    data class Failure(
        val e: Exception?
    ): Resource<Nothing>()
}