package com.example.footballmanager.ui.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.data.repository.FootballDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: FootballDataRepository) : ViewModel() {

    val teams: StateFlow<List<PlayerTeam>> =
        repository.observeTeams().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadTeams(league: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.refreshTeams(league).onFailure { _error.value = it.message }
            _isLoading.value = false
        }
    }

    /** HTTP request #2: fetch the squad for the tapped team. */
    fun loadSquad(team: PlayerTeam) {
        val remoteId = team.remoteId ?: return
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            repository.refreshPlayers(remoteId, team.id).onFailure { _error.value = it.message }
            _isLoading.value = false
        }
    }
}
