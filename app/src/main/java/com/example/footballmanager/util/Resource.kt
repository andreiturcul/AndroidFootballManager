package com.example.footballmanager.util

/** Generic wrapper used by ViewModels to expose loading/success/error UI state safely. */
sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}
