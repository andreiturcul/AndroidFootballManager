package com.example.footballmanager.ui.transfers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.data.local.entities.Transfer
import com.example.footballmanager.data.local.entities.UserTransferProposal
import com.example.footballmanager.data.repository.FootballDataRepository
import com.example.footballmanager.data.repository.TransferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class TransfersUiState(
    val transfers: List<Transfer> = emptyList(),
    val proposals: List<UserTransferProposal> = emptyList(),
    val players: List<Player> = emptyList(),
    val teams: List<PlayerTeam> = emptyList()
)

class TransfersViewModel(
    private val transferRepository: TransferRepository,
    private val footballDataRepository: FootballDataRepository,
    private val currentUserId: Long
) : ViewModel() {

    val uiState: StateFlow<TransfersUiState> = combine(
        transferRepository.observeTransfers(),
        transferRepository.observeProposals(),
        footballDataRepository.observePlayers(),
        footballDataRepository.observeTeams()
    ) { transfers, proposals, players, teams ->
        TransfersUiState(transfers, proposals, players, teams)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TransfersUiState())

    fun voteTransfer(transferId: Long, up: Boolean) {
        viewModelScope.launch { transferRepository.voteTransfer(transferId, currentUserId, up) }
    }

    fun voteProposal(proposalId: Long, up: Boolean) {
        viewModelScope.launch { transferRepository.voteProposal(proposalId, currentUserId, up) }
    }

    fun proposeTransfer(playerId: Long, fromTeamId: Long?, toTeamId: Long?) {
        viewModelScope.launch { transferRepository.proposeTransfer(currentUserId, playerId, fromTeamId, toTeamId) }
    }
}
