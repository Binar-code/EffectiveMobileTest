package com.example.domain.result

sealed interface Result<out T> {
    data class Success<T>(val data: T): Result<T>
    sealed class Error: Result<Nothing> {
        data class HttpError(
            val code: Int,
            val msg: String
        ): Error()

        data object Unknown: Error()
    }
}
