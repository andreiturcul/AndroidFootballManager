package com.example.footballmanager.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.FootballApp
import com.example.footballmanager.ui.components.FootballPage
import com.example.footballmanager.ui.components.FootballScene
import com.example.footballmanager.util.viewModelFactory

@Composable
fun RegisterScreen(
    onRegisterSuccess: (Long) -> Unit,
    onGoToLogin: () -> Unit
) {
    val app = FootballApp.INSTANCE
    val viewModel: RegisterViewModel = viewModel(
        factory = viewModelFactory { RegisterViewModel(app.authRepository, app.sessionManager) }
    )
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.registeredUserId) {
        state.registeredUserId?.let { onRegisterSuccess(it) }
    }

    FootballPage(FootballScene.STADIUM) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.94f))
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Create your account", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = state.name,
                    onValueChange = viewModel::onNameChange,
                    label = { Text("Full name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = state.email,
                    onValueChange = viewModel::onEmailChange,
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = state.password,
                    onValueChange = viewModel::onPasswordChange,
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )

                state.error?.let {
                    Spacer(Modifier.height(8.dp))
                    Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = viewModel::register,
                    enabled = !state.isLoading,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                    } else {
                        Text("Register")
                    }
                }
                Spacer(Modifier.height(12.dp))
                TextButton(onClick = onGoToLogin) {
                    Text("Already have an account? Log in")
                }
            }
        }
    }
}
