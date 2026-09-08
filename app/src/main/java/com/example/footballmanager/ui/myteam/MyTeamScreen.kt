@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.myteam

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.FormationPosition
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.ui.components.EmptyState
import com.example.footballmanager.ui.components.FootballPage
import com.example.footballmanager.ui.components.FootballScene
import com.example.footballmanager.ui.components.PriceTag
import com.example.footballmanager.ui.components.SectionTitle
import com.example.footballmanager.ui.components.footballTopBarColors
import com.example.footballmanager.util.TeamBudget
import com.example.footballmanager.util.viewModelFactory
import kotlin.math.roundToInt

private data class PitchDrag(
    val player: Player,
    val fromSlot: Int,
    val pointer: Offset,
    val hoverSlot: Int?
)

@Composable
fun MyTeamScreen(currentUserId: Long) {
    val app = FootballApp.INSTANCE
    val context = LocalContext.current
    val viewModel: MyTeamViewModel = viewModel(
        factory = viewModelFactory { MyTeamViewModel(app.userTeamRepository, app.footballDataRepository, currentUserId) }
    )
    val state by viewModel.uiState.collectAsState()
    var slotBeingEdited by remember { mutableStateOf<FormationPosition?>(null) }
    var boardDragging by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
            if (!granted) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    val totalSpent = remember(state.assignments) {
        state.assignments.values.sumOf { it.price }
    }

    LaunchedEffect(state.saveMessage) {
        state.saveMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearSaveMessage()
        }
    }

    FootballPage(FootballScene.PITCH) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text(state.userTeam?.name ?: "My Team") },
                    colors = footballTopBarColors()
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            userScrollEnabled = !boardDragging
        ) {
            item {
                BudgetCard(totalSpent = totalSpent, maxBudget = TeamBudget.MAX_EUROS_MILLIONS)
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
                SectionTitle("Tactical board")
                Text(
                    "Tap a slot to sign a player. Long-press and drag to move or swap players of the same position.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFD1FAE5)
                )
                Spacer(Modifier.height(8.dp))
            }

            if (state.positions.isEmpty()) {
                item { EmptyState("Choose a formation above to start adding players.") }
            } else {
                item {
                    PitchBoard(
                        positions = state.positions,
                        assignments = state.assignments,
                        onSlotTap = { slotBeingEdited = it },
                        onPlayerDropped = { from, to -> viewModel.movePlayer(from, to) },
                        onDragActive = { boardDragging = it }
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.saveTeam() },
                        enabled = totalSpent <= TeamBudget.MAX_EUROS_MILLIONS,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Team")
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
                maxBudget = TeamBudget.MAX_EUROS_MILLIONS,
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
                        text = "Dream Team Budget",
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

private fun pitchLayout(positions: List<FormationPosition>): Map<Int, Pair<Float, Float>> {
    val byPosition = positions.groupBy { it.position.uppercase() }
    val result = mutableMapOf<Int, Pair<Float, Float>>()
    fun place(list: List<FormationPosition>, y: Float) {
        val ordered = list.sortedBy { it.slotNumber }
        ordered.forEachIndexed { index, slot ->
            val x = (index + 1f) / (ordered.size + 1f)
            result[slot.slotNumber] = x to y
        }
    }
    place(byPosition["FWD"].orEmpty(), 0.16f)
    place(byPosition["MID"].orEmpty(), 0.42f)
    place(byPosition["DEF"].orEmpty(), 0.68f)
    place(byPosition["GK"].orEmpty(), 0.88f)
    positions.filter { it.slotNumber !in result }.forEach { slot ->
        result[slot.slotNumber] = 0.5f to 0.5f
    }
    return result
}

@Composable
private fun PitchBoard(
    positions: List<FormationPosition>,
    assignments: Map<Int, Player>,
    onSlotTap: (FormationPosition) -> Unit,
    onPlayerDropped: (fromSlot: Int, toSlot: Int) -> Unit,
    onDragActive: (Boolean) -> Unit
) {
    val layout = remember(positions) { pitchLayout(positions) }
    val slotRects = remember { mutableStateMapOf<Int, Rect>() }
    var drag by remember { mutableStateOf<PitchDrag?>(null) }
    val positionBySlot = remember(positions) {
        positions.associate { it.slotNumber to it.position }
    }

    Card(
        modifier = Modifier.fillMaxWidth().height(480.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            PitchBackground(Modifier.fillMaxSize())

            val slotWidth = 76.dp
            val slotHeight = 92.dp

            positions.forEach { slot ->
                val (fx, fy) = layout[slot.slotNumber] ?: (0.5f to 0.5f)
                val x = maxWidth * fx - slotWidth / 2
                val y = maxHeight * fy - slotHeight / 2
                val assigned = assignments[slot.slotNumber]
                val isHover = drag?.hoverSlot == slot.slotNumber && drag?.fromSlot != slot.slotNumber
                val hoverCompatible = isHover && positionBySlot[drag?.fromSlot] == slot.position
                val isOrigin = drag?.fromSlot == slot.slotNumber

                Box(
                    modifier = Modifier
                        .offset(x, y)
                        .size(slotWidth, slotHeight)
                        .zIndex(if (isOrigin) 2f else 1f)
                        .onGloballyPositioned { coords ->
                            slotRects[slot.slotNumber] = coords.boundsInParent()
                        }
                        .then(
                            if (isHover) {
                                Modifier.border(
                                    2.dp,
                                    if (hoverCompatible) Color(0xFFA5D6A7) else Color(0xFFEF9A9A),
                                    RoundedCornerShape(12.dp)
                                )
                            } else Modifier
                        )
                        .pointerInput(assigned, slot.slotNumber) {
                            detectTapGestures(onTap = { onSlotTap(slot) })
                        }
                        .pointerInput(assigned, slot.slotNumber) {
                            if (assigned == null) return@pointerInput
                            detectDragGesturesAfterLongPress(
                                onDragStart = { start ->
                                    val rect = slotRects[slot.slotNumber]
                                    val pointer = if (rect != null) {
                                        Offset(rect.left + start.x, rect.top + start.y)
                                    } else start
                                    drag = PitchDrag(assigned, slot.slotNumber, pointer, slot.slotNumber)
                                    onDragActive(true)
                                },
                                onDrag = { change, _ ->
                                    change.consume()
                                    val rect = slotRects[slot.slotNumber]
                                    val pointer = if (rect != null) {
                                        Offset(rect.left + change.position.x, rect.top + change.position.y)
                                    } else change.position
                                    val hover = slotRects.entries
                                        .firstOrNull { it.value.expand(18f).contains(pointer) }
                                        ?.key
                                    drag = drag?.copy(pointer = pointer, hoverSlot = hover)
                                },
                                onDragEnd = {
                                    val current = drag
                                    drag = null
                                    onDragActive(false)
                                    val target = current?.hoverSlot
                                    if (current != null && target != null && target != current.fromSlot) {
                                        onPlayerDropped(current.fromSlot, target)
                                    }
                                },
                                onDragCancel = {
                                    drag = null
                                    onDragActive(false)
                                }
                            )
                        }
                ) {
                    PitchSlotToken(
                        slot = slot,
                        player = assigned,
                        faded = isOrigin
                    )
                }
            }

            drag?.let { active ->
                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                (active.pointer.x - 38.dp.toPx()).roundToInt(),
                                (active.pointer.y - 46.dp.toPx()).roundToInt()
                            )
                        }
                        .size(76.dp, 92.dp)
                        .zIndex(10f)
                        .alpha(0.92f)
                ) {
                    PitchSlotToken(slot = positions.first { it.slotNumber == active.fromSlot }, player = active.player, faded = false)
                }
            }
        }
    }
}

private fun Rect.expand(amount: Float): Rect {
    return Rect(left - amount, top - amount, right + amount, bottom + amount)
}

@Composable
private fun PitchBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        drawRect(color = Color(0xFF1B4D2E))
        val stripeWidth = width / 8
        for (i in 0..7 step 2) {
            drawRect(
                color = Color(0xFF235C37),
                topLeft = Offset(i * stripeWidth, 0f),
                size = Size(stripeWidth, height)
            )
        }
        val lineStroke = Stroke(width = 3.5f)
        val white = Color.White.copy(alpha = 0.75f)
        val pad = 10f
        drawRect(
            color = white,
            topLeft = Offset(pad, pad),
            size = Size(width - pad * 2, height - pad * 2),
            style = lineStroke
        )
        drawLine(
            color = white,
            start = Offset(pad, height / 2),
            end = Offset(width - pad, height / 2),
            strokeWidth = 3.5f
        )
        drawCircle(
            color = white,
            radius = width / 6,
            center = Offset(width / 2, height / 2),
            style = lineStroke
        )
        val boxW = width * 0.55f
        val boxH = height * 0.16f
        drawRect(
            color = white,
            topLeft = Offset((width - boxW) / 2, pad),
            size = Size(boxW, boxH),
            style = lineStroke
        )
        drawRect(
            color = white,
            topLeft = Offset((width - boxW) / 2, height - pad - boxH),
            size = Size(boxW, boxH),
            style = lineStroke
        )
    }
}

@Composable
private fun PitchSlotToken(slot: FormationPosition, player: Player?, faded: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .alpha(if (faded) 0.35f else 1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(
                    color = if (player != null) Color.White.copy(alpha = 0.92f) else Color.White.copy(alpha = 0.28f),
                    shape = CircleShape
                )
                .border(2.dp, Color.White.copy(alpha = 0.9f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = player?.name?.split(" ")?.lastOrNull()?.take(8) ?: "+",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = if (player != null) Color(0xFF1B4D2E) else Color.White,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(2.dp))
        PositionBadge(position = slot.position)
        if (player != null) {
            Text(
                text = "€%.0fM".format(player.price),
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1
            )
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
