@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.footballmanager.FootballApp
import com.example.footballmanager.ui.components.FootballPage
import com.example.footballmanager.ui.components.FootballScene
import com.example.footballmanager.ui.components.footballTopBarColors
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(currentUserId: Long, onLoggedOut: () -> Unit) {
    val app = FootballApp.INSTANCE
    val scope = rememberCoroutineScope()
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }

    LaunchedEffect(currentUserId) {
        app.authRepository.getUser(currentUserId)?.let {
            userName = it.name
            userEmail = it.email
        }
    }

    FootballPage(FootballScene.CROWD) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = { TopAppBar(title = { Text("Profile") }, colors = footballTopBarColors()) }
        ) { padding ->
            Column(
                modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = Color.White
                )
                Spacer(Modifier.height(12.dp))
                Text(userName, style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Text(userEmail, style = MaterialTheme.typography.bodyMedium, color = Color(0xFFD1FAE5))
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = {
                        scope.launch {
                            app.sessionManager.clearSession()
                            onLoggedOut()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Log out")
                }
            }
        }
    }
}
