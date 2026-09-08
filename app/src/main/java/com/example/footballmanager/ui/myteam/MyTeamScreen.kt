@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.myteam

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.FormationPosition
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.ui.components.EmptyState
import com.example.footballmanager.ui.components.PriceTag
import com.example.footballmanager.ui.components.SectionTitle
import com.example.footballmanager.util.viewModelFactory

private const val MAX_BUDGET = 900.0 // 900 Million Euros

@Composable
fun MyTeamScreen(currentUserId: Long) {
    val app = FootballApp.INSTANCE
    val viewModel: MyTeamViewModel = viewModel(
        factory = viewModelFactory { MyTeamViewModel(app.userTeamRepository, app.footballDataRepository, currentUserId) }
    )
    val state by viewModel.uiState.collectAsState()
    var slotBeingEdited by remember { mutableStateOf<FormationPosition?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    val totalSpent = remember(state.assignments) {
        state.assignments.values.sumOf { it.price }
    }

    LaunchedEffect(state.saveMessage) {
        state.saveMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearSaveMessage()
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(state.userTeam?.name ?: "My Team") }) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                BudgetCard(totalSpent = totalSpent, maxBudget = MAX_BUDGET)
                Spacer(Modifier.height(16.dp))

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

                SectionTitle("Tactics")
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
                SectionTitle("Starting XI (${state.positions.size} slots)")
            }

            if (state.positions.isEmpty()) {
                item { EmptyState("Choose a formation above to start adding players.") }
            } else {
                item {
                    FootballPitchHeader()
                    Spacer(Modifier.height(12.dp))
                }

                items(state.positions, key = { it.id }) { slot ->
                    SlotRow(
                        slot = slot,
                        assignedPlayer = state.assignments[slot.slotNumber],
                        onTap = { slotBeingEdited = slot }
                    )
                    Spacer(Modifier.height(6.dp))
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.saveTeam() },
                        enabled = totalSpent <= MAX_BUDGET,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (state.userTeam?.submitted == true) "Save Team" else "Save Team")
                    }
                }
            }
        }
    }

    slotBeingEdited?.let { targetSlot ->
        val assignedIds = state.assignments.values.map { it.id }.toSet()
        val currentPlayer = state.assignments[targetSlot.slotNumber]
        PlayerPickerDialog(
            slot = targetSlot,
            currentSlotPlayer = currentPlayer,
            totalSpent = totalSpent,
            maxBudget = MAX_BUDGET,
            allPlayers = state.allPlayers,
            assignedPlayerIds = assignedIds,
            onDismiss = { slotBeingEdited = null },
            onPick = { player ->
                viewModel.assignPlayer(targetSlot.slotNumber, player)
                slotBeingEdited = null
            }
        )
    }
}

@Composable
private fun BudgetCard(totalSpent: Double, maxBudget: Double) {
    val remaining = maxBudget - totalSpent
    val progress = (totalSpent / maxBudget).toFloat().coerceIn(0f, 1f)
    val isOverBudget = totalSpent > maxBudget

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isOverBudget) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "💰 Dream Team Budget",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Max Budget: €${maxBudget.toInt()}M",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "€%.1fM / €%.1fM".format(totalSpent, maxBudget),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        color = if (isOverBudget) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = if (remaining >= 0) "Remaining: €%.1fM".format(remaining) else "Over Limit!",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = if (isOverBudget) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = if (isOverBudget) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

@Composable
fun PositionBadge(position: String, modifier: Modifier = Modifier) {
    val (bgColor, textColor) = when (position.uppercase()) {
        "GK" -> Color(0xFFE65100) to Color.White
        "DEF" -> Color(0xFF1565C0) to Color.White
        "MID" -> Color(0xFF2E7D32) to Color.White
        "FWD" -> Color(0xFFC62828) to Color.White
        else -> MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
    }
    Surface(
        color = bgColor,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
    ) {
        Text(
            text = position,
            color = textColor,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}

@Composable
private fun FootballPitchHeader() {
    Card(
        modifier = Modifier.fillMaxWidth().height(80.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                // Grass pitch background
                drawRect(color = Color(0xFF1B4D2E))
                val stripeWidth = width / 6
                for (i in 0..5 step 2) {
                    drawRect(
                        color = Color(0xFF235C37),
                        topLeft = Offset(i * stripeWidth, 0f),
                        size = Size(stripeWidth, height)
                    )
                }

                // White pitch markings
                val lineStroke = Stroke(width = 3f)
                val white = Color.White.copy(alpha = 0.7f)

                // Outer border
                drawRect(color = white, topLeft = Offset(8f, 8f), size = Size(width - 16f, height - 16f), style = lineStroke)

                // Halfway line & Center circle
                drawLine(color = white, start = Offset(width / 2, 8f), end = Offset(width / 2, height - 8f), strokeWidth = 3f)
                drawCircle(color = white, radius = height / 3, center = Offset(width / 2, height / 2), style = lineStroke)
            }
            Text(
                text = "⚽ TACTICAL PITCH",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Center).background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(8.dp)).padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun SlotRow(slot: FormationPosition, assignedPlayer: Player?, onTap: () -> Unit) {
    ElevatedCard(
        onClick = onTap,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (assignedPlayer != null) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PositionBadge(position = slot.position)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = assignedPlayer?.name ?: "Tap to add (${slot.position})",
                        style = if (assignedPlayer != null) MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodyMedium,
                        color = if (assignedPlayer != null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text("Slot ${slot.slotNumber}", style = MaterialTheme.typography.labelSmall)
                }
            }
            if (assignedPlayer != null) {
                PriceTag(price = assignedPlayer.price)
            }
        }
    }
}

@Composable
private fun PlayerPickerDialog(
    slot: FormationPosition,
    currentSlotPlayer: Player?,
    totalSpent: Double,
    maxBudget: Double,
    allPlayers: List<Player>,
    assignedPlayerIds: Set<Long>,
    onDismiss: () -> Unit,
    onPick: (Player) -> Unit
) {
    val targetPosition = slot.position
    val availablePlayers = remember(allPlayers, assignedPlayerIds, targetPosition) {
        allPlayers
            .filter { player ->
                player.position.equals(targetPosition, ignoreCase = true) && player.id !in assignedPlayerIds
            }
            .sortedByDescending { it.price }
    }

    val currentSlotPrice = currentSlotPlayer?.price ?: 0.0
    val maxAllowedForSlot = (maxBudget - totalSpent) + currentSlotPrice

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PositionBadge(position = targetPosition)
                Spacer(Modifier.width(8.dp))
                Text("Select $targetPosition")
            }
        },
        text = {
            Column {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                ) {
                    Text(
                        text = "Slot Budget Capacity: €%.1fM".format(maxAllowedForSlot.coerceAtLeast(0.0)),
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                if (availablePlayers.isEmpty()) {
                    Text(
                        "No available players for position $targetPosition.\nCheck if they are already in your team or fetch more from Teams tab.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 360.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(availablePlayers, key = { it.id }) { player ->
                            val canAfford = player.price <= maxAllowedForSlot
                            ElevatedCard(
                                onClick = { if (canAfford) onPick(player) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = if (canAfford) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        PositionBadge(position = player.position)
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = player.name,
                                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                                color = if (canAfford) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            if (!canAfford) {
                                                Text(
                                                    text = "Exceeds €900M Limit",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = MaterialTheme.colorScheme.error
                                                )
                                            }
                                        }
                                    }
                                    PriceTag(price = player.price)
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Close") } }
    )
}
