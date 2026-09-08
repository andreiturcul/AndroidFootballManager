@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.footballmanager.FootballApp
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

    Scaffold(topBar = { TopAppBar(title = { Text("Profile") }) }) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.AccountCircle, contentDescription = null, modifier = Modifier.size(80.dp))
            Spacer(Modifier.height(12.dp))
            Text(userName, style = MaterialTheme.typography.headlineMedium)
            Text(userEmail, style = MaterialTheme.typography.bodyMedium)
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
