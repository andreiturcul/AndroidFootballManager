@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.teams

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.ui.components.EmptyState
import com.example.footballmanager.ui.components.ErrorBanner
import com.example.footballmanager.util.viewModelFactory

private val LEAGUES = listOf("English_Premier_League", "Spanish_La_Liga", "Italian_Serie_A", "German_Bundesliga")

/** Requirement: 2nd HTTP endpoint is triggered from here (squad lookup per team). */
@Composable
fun TeamsScreen(onTeamSelected: (Long) -> Unit) {
    val app = FootballApp.INSTANCE
    val viewModel: TeamsViewModel = viewModel(
        factory = viewModelFactory { TeamsViewModel(app.footballDataRepository) }
    )
    val teams by viewModel.teams.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    var selectedLeague by remember { mutableStateOf(LEAGUES.first()) }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(topBar = { TopAppBar(title = { Text("Teams") }) }) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }, modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = selectedLeague.replace('_', ' '),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("League") },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        LEAGUES.forEach { league ->
                            DropdownMenuItem(text = { Text(league.replace('_', ' ')) }, onClick = {
                                selectedLeague = league
                                expanded = false
                            })
                        }
                    }
                }
                Spacer(Modifier.width(8.dp))
                Button(onClick = { viewModel.loadTeams(selectedLeague) }) { Text("Fetch") }
            }

            error?.let { ErrorBanner(it) }
            if (isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            if (teams.isEmpty() && !isLoading) {
                EmptyState("No teams cached yet. Pick a league and tap Fetch.")
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(teams, key = { it.id }) { team ->
                        TeamRow(team = team, onClick = {
                            viewModel.loadSquad(team)
                            onTeamSelected(team.id)
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun TeamRow(team: PlayerTeam, onClick: () -> Unit) {
    ElevatedCard(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            if (!team.badgeUrl.isNullOrBlank()) {
                AsyncImage(
                    model = team.badgeUrl,
                    contentDescription = team.name,
                    modifier = Modifier.size(44.dp)
                )
                Spacer(Modifier.width(12.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(team.name, style = MaterialTheme.typography.titleMedium)
                Text(team.league ?: "", style = MaterialTheme.typography.bodyMedium)
            }
            Text("👍 ${team.votes}")
        }
    }
}
