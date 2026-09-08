@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.players

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.ui.components.EmptyState
import com.example.footballmanager.ui.components.ErrorBanner
import com.example.footballmanager.ui.components.PriceTag
import com.example.footballmanager.util.viewModelFactory

/** Requirement: "store data into local DB + show data in a scrollable list". */
@Composable
fun PlayersScreen(onPlayerClick: (Long) -> Unit) {
    val app = FootballApp.INSTANCE
    val viewModel: PlayersViewModel = viewModel(
        factory = viewModelFactory { PlayersViewModel(app.footballDataRepository) }
    )
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Players") },
                actions = {
                    IconButton(onClick = { viewModel.refreshFromApi() }) {
                        Icon(Icons.Filled.Refresh, contentDescription = "Refresh from API")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            state.error?.let { ErrorBanner(it) }
            if (state.isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            if (state.players.isEmpty() && !state.isLoading) {
                EmptyState("No players cached yet. Tap refresh to fetch data from the API.")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.players, key = { it.id }) { player ->
                        PlayerRow(player = player, teamName = state.teams.find { t -> t.id == player.teamId }?.name, onClick = { onPlayerClick(player.id) })
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerRow(player: Player, teamName: String?, onClick: () -> Unit) {
    ElevatedCard(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(player.name, style = MaterialTheme.typography.titleMedium)
                Text("${player.position}${teamName?.let { " · $it" } ?: ""}", style = MaterialTheme.typography.bodyMedium)
            }
            PriceTag(price = player.price)
        }
    }
}
