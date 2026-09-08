@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.players

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.ui.components.PriceTag

@Composable
fun PlayerDetailScreen(
    playerId: Long,
    onProposeTransfer: (Long) -> Unit,
    onBack: () -> Unit
) {
    val app = FootballApp.INSTANCE
    var player by remember { mutableStateOf<Player?>(null) }
    var team by remember { mutableStateOf<PlayerTeam?>(null) }

    LaunchedEffect(playerId) {
        player = app.footballDataRepository.getPlayer(playerId)
        player?.teamId?.let { team = app.footballDataRepository.getTeam(it) }
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Player details") }) }) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize()) {
            player?.let { p ->
                Text(p.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(4.dp))
                Text(p.nickname ?: "", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(16.dp))
                Text("Position: ${p.position}")
                Text("Team: ${team?.name ?: "Free agent"}")
                Spacer(Modifier.height(8.dp))
                PriceTag(price = p.price)
                Spacer(Modifier.height(24.dp))
                Button(onClick = { onProposeTransfer(p.id) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Propose transfer")
                }
                Spacer(Modifier.height(8.dp))
                OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                    Text("Back")
                }
            } ?: run {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
