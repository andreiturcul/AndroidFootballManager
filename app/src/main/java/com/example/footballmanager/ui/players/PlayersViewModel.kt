package com.example.footballmanager.ui.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.data.repository.FootballDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class PlayersUiState(
    val players: List<Player> = emptyList(),
    val teams: List<PlayerTeam> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class PlayersViewModel(private val repository: FootballDataRepository) : ViewModel() {

    private val loading = MutableStateFlow(false)
    private val errorMsg = MutableStateFlow<String?>(null)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        viewModelScope.launch {
            repository.cleanupAndSeed()
        }
    }

    val uiState: StateFlow<PlayersUiState> = combine(
        repository.observePlayers(),
        repository.observeTeams(),
        loading,
        errorMsg,
        _searchQuery
    ) { players, teams, isLoading, error, query ->
        val filteredPlayers = if (query.isBlank()) {
            players
        } else {
            players.filter { it.name.contains(query, ignoreCase = true) }
        }
        PlayersUiState(filteredPlayers, teams, isLoading, error)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PlayersUiState())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    /** Fetches teams (HTTP #1) and caches them via Room. */
    fun refreshFromApi(league: String = "English_Premier_League") {
        viewModelScope.launch {
            loading.value = true
            errorMsg.value = null
            repository.refreshTeams(league)
                .onFailure { errorMsg.value = "Could not load teams: ${it.message}" }
            loading.value = false
        }
    }

    fun refreshPlayersForTeam(remoteTeamId: String, localTeamId: Long) {
        viewModelScope.launch {
            loading.value = true
            repository.refreshPlayers(remoteTeamId, localTeamId)
                .onFailure { errorMsg.value = "Could not load players: ${it.message}" }
            loading.value = false
        }
    }
}
