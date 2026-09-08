package com.example.footballmanager.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Small generic factory so every screen can build its ViewModel via a lambda,
 * e.g. viewModel(factory = viewModelFactory { LoginViewModel(app.authRepository, app.sessionManager) })
 * Keeps the project free of a DI framework while still allowing constructor injection.
 */
class GenericViewModelFactory<T : ViewModel>(private val creator: () -> T) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <U : ViewModel> create(modelClass: Class<U>): U = creator() as U
}

fun <T : ViewModel> viewModelFactory(creator: () -> T): GenericViewModelFactory<T> = GenericViewModelFactory(creator)
