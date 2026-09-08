@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.transfers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballmanager.FootballApp
import com.example.footballmanager.data.local.entities.Transfer
import com.example.footballmanager.data.local.entities.UserTransferProposal
import com.example.footballmanager.ui.components.EmptyState
import com.example.footballmanager.ui.components.FootballPage
import com.example.footballmanager.ui.components.FootballScene
import com.example.footballmanager.ui.components.SectionTitle
import com.example.footballmanager.ui.components.VoteButtons
import com.example.footballmanager.ui.components.footballTopBarColors
import com.example.footballmanager.util.viewModelFactory
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransfersScreen(currentUserId: Long) {
    val app = FootballApp.INSTANCE
    val viewModel: TransfersViewModel = viewModel(
        factory = viewModelFactory { TransfersViewModel(app.transferRepository, app.footballDataRepository, currentUserId) }
    )
    val state by viewModel.uiState.collectAsState()

    FootballPage(FootballScene.TRANSFERS) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = { TopAppBar(title = { Text("Transfers") }, colors = footballTopBarColors()) }
        ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize(), contentPadding = PaddingValues(12.dp)) {
            item { SectionTitle("Confirmed transfers") }
            if (state.transfers.isEmpty()) {
                item { EmptyState("No transfers recorded yet.") }
            } else {
                items(state.transfers, key = { "t${it.id}" }) { transfer ->
                    TransferRow(
                        transfer = transfer,
                        playerName = state.players.find { it.id == transfer.playerId }?.name ?: "Unknown player",
                        fromName = state.teams.find { it.id == transfer.fromTeamId }?.name,
                        toName = state.teams.find { it.id == transfer.toTeamId }?.name,
                        onVote = { up -> viewModel.voteTransfer(transfer.id, up) }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }

            item { Spacer(Modifier.height(16.dp)); SectionTitle("Community transfer proposals") }
            if (state.proposals.isEmpty()) {
                item { EmptyState("No proposals yet. Open a player and propose one!") }
            } else {
                items(state.proposals, key = { "p${it.id}" }) { proposal ->
                    ProposalRow(
                        proposal = proposal,
                        playerName = state.players.find { it.id == proposal.playerId }?.name ?: "Unknown player",
                        fromName = state.teams.find { it.id == proposal.fromTeamId }?.name,
                        toName = state.teams.find { it.id == proposal.toTeamId }?.name,
                        onVote = { up -> viewModel.voteProposal(proposal.id, up) }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
        }
    }
}

@Composable
private fun TransferRow(transfer: Transfer, playerName: String, fromName: String?, toName: String?, onVote: (Boolean) -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text(playerName, style = MaterialTheme.typography.titleMedium)
            Text("${fromName ?: "Free agent"} → ${toName ?: "Free agent"}", style = MaterialTheme.typography.bodyMedium)
            Text(
                "€%.1fM · %s".format(transfer.price, SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(transfer.date))),
                style = MaterialTheme.typography.bodyMedium
            )
            VoteButtons(votes = transfer.votes, onUpvote = { onVote(true) }, onDownvote = { onVote(false) })
        }
    }
}

@Composable
private fun ProposalRow(
    proposal: UserTransferProposal,
    playerName: String,
    fromName: String?,
    toName: String?,
    onVote: (Boolean) -> Unit
) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text(playerName, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Proposed move: ${fromName ?: "Current Club"} → ${toName ?: "Unknown Destination"}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            VoteButtons(votes = proposal.votes, onUpvote = { onVote(true) }, onDownvote = { onVote(false) })
        }
    }
}
