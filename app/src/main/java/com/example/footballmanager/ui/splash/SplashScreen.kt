package com.example.footballmanager.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.footballmanager.FootballApp
import kotlinx.coroutines.flow.first

/**
 * Reads the persisted session (DataStore) once, then routes to Home if a
 * user id is already stored, or to Login otherwise.
 */
@Composable
fun SplashScreen(
    onSessionFound: (Long) -> Unit,
    onNoSession: () -> Unit
) {
    val app = FootballApp.INSTANCE
    LaunchedEffect(Unit) {
        val userId = app.sessionManager.userIdFlow.first()
        if (userId != null) onSessionFound(userId) else onNoSession()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
