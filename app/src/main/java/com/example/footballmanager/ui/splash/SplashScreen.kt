package com.example.footballmanager.ui.splash

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.footballmanager.FootballApp
import com.example.footballmanager.ui.components.FootballPage
import com.example.footballmanager.ui.components.FootballScene
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
    FootballPage(FootballScene.PITCH) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.White
        )
    }
}
