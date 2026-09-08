@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.teams

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.ui.components.EmptyState
import com.example.footballmanager.ui.components.ErrorBanner
import com.example.footballmanager.util.viewModelFactory

private val LEAGUES = listOf(
    "All Leagues",
    "English Premier League",
    "Spanish La Liga",
    "Italian Serie A",
    "German Bundesliga",
    "French Ligue 1",
    "Portuguese Primeira Liga",
    "Dutch Eredivisie"
)

data class TrophyInfo(val name: String, val count: String, val icon: String = "🏆")

fun getTeamTrophies(teamName: String, league: String?): List<TrophyInfo> {
    return when (teamName.lowercase().trim()) {
        "real madrid" -> listOf(
            TrophyInfo("UEFA Champions League", "15x", "🏆"),
            TrophyInfo("La Liga Titles", "36x", "🥇"),
            TrophyInfo("Copa del Rey", "20x", "👑"),
            TrophyInfo("FIFA Club World Cup", "5x", "🌍"),
            TrophyInfo("UEFA Super Cup", "6x", "⭐")
        )
        "barcelona" -> listOf(
            TrophyInfo("UEFA Champions League", "5x", "🏆"),
            TrophyInfo("La Liga Titles", "27x", "🥇"),
            TrophyInfo("Copa del Rey", "31x", "👑"),
            TrophyInfo("FIFA Club World Cup", "3x", "🌍"),
            TrophyInfo("UEFA Super Cup", "5x", "⭐")
        )
        "manchester city" -> listOf(
            TrophyInfo("UEFA Champions League", "1x", "🏆"),
            TrophyInfo("Premier League Titles", "10x", "🥇"),
            TrophyInfo("FA Cup", "7x", "👑"),
            TrophyInfo("FIFA Club World Cup", "1x", "🌍"),
            TrophyInfo("EFL Cup", "8x", "⭐")
        )
        "arsenal" -> listOf(
            TrophyInfo("Premier League / 1st Div", "13x", "🥇"),
            TrophyInfo("FA Cup", "14x", "👑"),
            TrophyInfo("EFL Cup", "2x", "⭐"),
            TrophyInfo("FA Community Shield", "17x", "🛡️")
        )
        "liverpool" -> listOf(
            TrophyInfo("UEFA Champions League", "6x", "🏆"),
            TrophyInfo("Premier League / 1st Div", "19x", "🥇"),
            TrophyInfo("FA Cup", "8x", "👑"),
            TrophyInfo("FIFA Club World Cup", "1x", "🌍"),
            TrophyInfo("EFL Cup", "10x", "⭐")
        )
        "bayern munich" -> listOf(
            TrophyInfo("UEFA Champions League", "6x", "🏆"),
            TrophyInfo("Bundesliga Titles", "33x", "🥇"),
            TrophyInfo("DFB-Pokal", "20x", "👑"),
            TrophyInfo("FIFA Club World Cup", "2x", "🌍")
        )
        "inter milan", "inter" -> listOf(
            TrophyInfo("UEFA Champions League", "3x", "🏆"),
            TrophyInfo("Serie A Titles", "20x", "🥇"),
            TrophyInfo("Coppa Italia", "9x", "👑"),
            TrophyInfo("FIFA Club World Cup", "1x", "🌍")
        )
        "ac milan" -> listOf(
            TrophyInfo("UEFA Champions League", "7x", "🏆"),
            TrophyInfo("Serie A Titles", "19x", "🥇"),
            TrophyInfo("Coppa Italia", "5x", "👑"),
            TrophyInfo("UEFA Super Cup", "5x", "⭐")
        )
        "juventus" -> listOf(
            TrophyInfo("UEFA Champions League", "2x", "🏆"),
            TrophyInfo("Serie A Titles", "36x", "🥇"),
            TrophyInfo("Coppa Italia", "15x", "👑")
        )
        "psg", "paris saint-germain" -> listOf(
            TrophyInfo("Ligue 1 Titles", "12x", "🥇"),
            TrophyInfo("Coupe de France", "15x", "👑"),
            TrophyInfo("Coupe de la Ligue", "9x", "⭐")
        )
        "atletico madrid" -> listOf(
            TrophyInfo("La Liga Titles", "11x", "🥇"),
            TrophyInfo("Copa del Rey", "10x", "👑"),
            TrophyInfo("UEFA Europa League", "3x", "🏆")
        )
        "borussia dortmund" -> listOf(
            TrophyInfo("UEFA Champions League", "1x", "🏆"),
            TrophyInfo("Bundesliga Titles", "8x", "🥇"),
            TrophyInfo("DFB-Pokal", "5x", "👑")
        )
        "bayer leverkusen" -> listOf(
            TrophyInfo("Bundesliga Titles", "1x", "🥇"),
            TrophyInfo("DFB-Pokal", "2x", "👑"),
            TrophyInfo("UEFA Cup", "1x", "🏆")
        )
        "chelsea" -> listOf(
            TrophyInfo("UEFA Champions League", "2x", "🏆"),
            TrophyInfo("Premier League Titles", "6x", "🥇"),
            TrophyInfo("FA Cup", "8x", "👑"),
            TrophyInfo("UEFA Europa League", "2x", "⭐")
        )
        "manchester united" -> listOf(
            TrophyInfo("UEFA Champions League", "3x", "🏆"),
            TrophyInfo("Premier League Titles", "20x", "🥇"),
            TrophyInfo("FA Cup", "13x", "👑"),
            TrophyInfo("FIFA Club World Cup", "1x", "🌍")
        )
        "tottenham hotspur" -> listOf(
            TrophyInfo("First Division Titles", "2x", "🥇"),
            TrophyInfo("FA Cup", "8x", "👑"),
            TrophyInfo("UEFA Cup", "2x", "🏆")
        )
        "aston villa" -> listOf(
            TrophyInfo("European Cup (UCL)", "1x", "🏆"),
            TrophyInfo("First Division Titles", "7x", "🥇"),
            TrophyInfo("FA Cup", "7x", "👑")
        )
        "napoli" -> listOf(
            TrophyInfo("Serie A Titles", "3x", "🥇"),
            TrophyInfo("Coppa Italia", "6x", "👑"),
            TrophyInfo("UEFA Cup", "1x", "🏆")
        )
        "monaco" -> listOf(
            TrophyInfo("Ligue 1 Titles", "8x", "🥇"),
            TrophyInfo("Coupe de France", "5x", "👑")
        )
        else -> listOf(
            TrophyInfo("National League Titles", "5x+", "🥇"),
            TrophyInfo("National Cup Trophies", "4x+", "👑"),
            TrophyInfo("Continental Cup Appearances", "10x+", "🏆")
        )
    }
}

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
    var selectedLeague by remember { mutableStateOf(LEAGUES[1]) }
    var expanded by remember { mutableStateOf(false) }
    var selectedTeamForTrophies by remember { mutableStateOf<PlayerTeam?>(null) }

    LaunchedEffect(Unit) {
        if (teams.isEmpty()) {
            viewModel.loadTeams(selectedLeague)
        }
    }

    val filteredTeams = remember(teams, selectedLeague) {
        if (selectedLeague == "All Leagues") teams
        else teams.filter { team ->
            val leagueName = team.league?.replace('_', ' ') ?: ""
            leagueName.equals(selectedLeague, ignoreCase = true) ||
                    (selectedLeague.contains("Premier", ignoreCase = true) && leagueName.contains("Premier", ignoreCase = true)) ||
                    (selectedLeague.contains("La Liga", ignoreCase = true) && leagueName.contains("La Liga", ignoreCase = true)) ||
                    (selectedLeague.contains("Serie A", ignoreCase = true) && leagueName.contains("Serie A", ignoreCase = true)) ||
                    (selectedLeague.contains("Bundesliga", ignoreCase = true) && leagueName.contains("Bundesliga", ignoreCase = true)) ||
                    (selectedLeague.contains("Ligue 1", ignoreCase = true) && leagueName.contains("Ligue 1", ignoreCase = true))
        }
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Teams") }) }) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().padding(12.dp)) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedLeague,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Filter League") },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    LEAGUES.forEach { league ->
                        DropdownMenuItem(text = { Text(league) }, onClick = {
                            selectedLeague = league
                            expanded = false
                            if (league != "All Leagues") {
                                viewModel.loadTeams(league)
                            }
                        })
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            error?.let { ErrorBanner(it) }
            if (isLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

            if (filteredTeams.isEmpty() && !isLoading) {
                EmptyState("No teams cached for $selectedLeague yet.")
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredTeams, key = { it.id }) { team ->
                        TeamRow(team = team, onClick = {
                            selectedTeamForTrophies = team
                        })
                    }
                }
            }
        }
    }

    selectedTeamForTrophies?.let { team ->
        TeamTrophiesDialog(team = team, onDismiss = { selectedTeamForTrophies = null })
    }
}

@Composable
private fun TeamTrophiesDialog(team: PlayerTeam, onDismiss: () -> Unit) {
    val trophies = getTeamTrophies(team.name, team.league)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!team.badgeUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = team.badgeUrl,
                        contentDescription = team.name,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                }
                Column {
                    Text(team.name, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                    Text(team.league ?: "Official Club", style = MaterialTheme.typography.labelMedium)
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "🏆 Official Club Honors & Trophies",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(12.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.heightIn(max = 280.dp)
                ) {
                    items(trophies) { trophy ->
                        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(trophy.icon, style = MaterialTheme.typography.titleMedium)
                                    Spacer(Modifier.width(10.dp))
                                    Text(trophy.name, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                                }
                                Surface(
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Text(
                                        text = trophy.count,
                                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
                                        color = MaterialTheme.colorScheme.onSecondary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
private fun TeamRow(team: PlayerTeam, onClick: () -> Unit) {
    ElevatedCard(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(44.dp),
                contentAlignment = Alignment.Center
            ) {
                if (!team.badgeUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = team.badgeUrl,
                        contentDescription = team.name,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Shield,
                        contentDescription = "Shield",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(team.name, style = MaterialTheme.typography.titleMedium)
                Text(team.league ?: "", style = MaterialTheme.typography.bodyMedium)
            }
            Text("🏆 Trophies")
        }
    }
}
