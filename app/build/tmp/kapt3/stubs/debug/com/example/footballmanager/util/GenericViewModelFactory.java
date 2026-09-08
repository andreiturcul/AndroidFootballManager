package com.example.footballmanager.util;

/**
 * Small generic factory so every screen can build its ViewModel via a lambda,
 * e.g. viewModel(factory = viewModelFactory { LoginViewModel(app.authRepository, app.sessionManager) })
 * Keeps the project free of a DI framework while still allowing constructor injection.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u00a2\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0001\u0010\b*\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0016\u00a2\u0006\u0002\u0010\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/footballmanager/util/GenericViewModelFactory;", "T", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "creator", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "create", "U", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
public final class GenericViewModelFactory<T extends androidx.lifecycle.ViewModel> implements androidx.lifecycle.ViewModelProvider.Factory {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<T> creator = null;
    
    public GenericViewModelFactory(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<? extends T> creator) {
        super();
    }
    
    @java.lang.Override()
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    @org.jetbrains.annotations.NotNull()
    public <U extends androidx.lifecycle.ViewModel>U create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<U> modelClass) {
        return null;
    }
}