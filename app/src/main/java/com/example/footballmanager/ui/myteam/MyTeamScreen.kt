@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.myteam

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.FormationPosition
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.ui.components.EmptyState
import com.example.footballmanager.ui.components.SectionTitle
import com.example.footballmanager.util.viewModelFactory

@Composable
fun MyTeamScreen(currentUserId: Long) {
    val app = FootballApp.INSTANCE
    val viewModel: MyTeamViewModel = viewModel(
        factory = viewModelFactory { MyTeamViewModel(app.userTeamRepository, app.footballDataRepository, currentUserId) }
    )
    val state by viewModel.uiState.collectAsState()
    var slotBeingEdited by remember { mutableStateOf<Int?>(null) }

    Scaffold(topBar = { TopAppBar(title = { Text(state.userTeam?.name ?: "My Team") }) }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
            item {
                SectionTitle("Formation")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    state.formations.forEach { formation ->
                        FilterChip(
                            selected = state.userTeam?.formationId == formation.id,
                            onClick = { viewModel.selectFormation(formation.id) },
                            label = { Text(formation.name) }
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))

                SectionTitle("Tactic")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    state.tactics.forEach { tactic ->
                        FilterChip(
                            selected = state.userTeam?.tacticId == tactic.id,
                            onClick = { viewModel.selectTactic(tactic.id) },
                            label = { Text(tactic.style.name.lowercase().replaceFirstChar { c -> c.uppercase() }) }
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                SectionTitle("Lineup (${state.positions.size} slots)")
            }

            if (state.positions.isEmpty()) {
                item { EmptyState("Pick a formation above to start assigning players.") }
            } else {
                items(state.positions, key = { it.id }) { slot ->
                    SlotRow(
                        slot = slot,
                        assignedPlayer = state.assignments[slot.slotNumber],
                        onTap = { slotBeingEdited = slot.slotNumber }
                    )
                    Spacer(Modifier.height(6.dp))
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.submitTeam() },
                        enabled = state.userTeam?.submitted == false,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (state.userTeam?.submitted == true) "Submitted ✓" else "Submit team")
                    }
                }
            }
        }
    }

    slotBeingEdited?.let { slotNumber ->
        PlayerPickerDialog(
            players = state.allPlayers,
            onDismiss = { slotBeingEdited = null },
            onPick = { player ->
                viewModel.assignPlayer(slotNumber, player)
                slotBeingEdited = null
            }
        )
    }
}

@Composable
private fun SlotRow(slot: FormationPosition, assignedPlayer: Player?, onTap: () -> Unit) {
    ElevatedCard(onClick = onTap, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Slot ${slot.slotNumber} · ${slot.position}", style = MaterialTheme.typography.bodyMedium)
            Text(assignedPlayer?.name ?: "Tap to assign", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
private fun PlayerPickerDialog(players: List<Player>, onDismiss: () -> Unit, onPick: (Player) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose a player") },
        text = {
            if (players.isEmpty()) {
                Text("No players available yet — fetch some from the Teams tab first.")
            } else {
                LazyColumn(modifier = Modifier.heightIn(max = 320.dp)) {
                    items(players, key = { it.id }) { player ->
                        TextButton(onClick = { onPick(player) }, modifier = Modifier.fillMaxWidth()) {
                            Text("${player.name} (${player.position})", modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Close") } }
    )
}
