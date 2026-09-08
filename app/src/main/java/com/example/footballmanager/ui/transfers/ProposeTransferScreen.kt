@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.transfers

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.data.local.entities.PlayerTeam
import kotlinx.coroutines.launch

@Composable
fun ProposeTransferScreen(
    playerId: Long,
    currentUserId: Long,
    onDone: () -> Unit
) {
    val app = FootballApp.INSTANCE
    val scope = rememberCoroutineScope()
    var player by remember { mutableStateOf<Player?>(null) }
    var teams by remember { mutableStateOf<List<PlayerTeam>>(emptyList()) }
    var toTeamId by remember { mutableStateOf<Long?>(null) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(playerId) {
        player = app.footballDataRepository.getPlayer(playerId)
    }

    // simple one-shot collection of the current team list for the dropdown
    LaunchedEffect(Unit) {
        app.footballDataRepository.observeTeams().collect { teams = it }
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Propose transfer") }) }) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize()) {
            player?.let { Text("Player: ${it.name}", style = MaterialTheme.typography.titleMedium) }
            Spacer(Modifier.height(16.dp))

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                OutlinedTextField(
                    value = teams.find { it.id == toTeamId }?.name ?: "Select destination team",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Move to") },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    teams.forEach { team ->
                        DropdownMenuItem(text = { Text(team.name) }, onClick = { toTeamId = team.id; expanded = false })
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    val p = player ?: return@Button
                    scope.launch {
                        app.transferRepository.proposeTransfer(currentUserId, p.id, p.teamId, toTeamId)
                        onDone()
                    }
                },
                enabled = player != null && toTeamId != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit proposal")
            }
        }
    }
}
