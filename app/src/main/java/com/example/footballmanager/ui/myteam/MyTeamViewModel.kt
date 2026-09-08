package com.example.footballmanager.ui.myteam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.data.local.entities.*
import com.example.footballmanager.data.repository.FootballDataRepository
import com.example.footballmanager.data.repository.UserTeamRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MyTeamUiState(
    val userTeam: UserTeam? = null,
    val formations: List<TeamFormation> = emptyList(),
    val tactics: List<Tactic> = emptyList(),
    val positions: List<FormationPosition> = emptyList(),
    val assignments: Map<Int, Player> = emptyMap(), // slotNumber -> player
    val allPlayers: List<Player> = emptyList()
)

class MyTeamViewModel(
    private val repository: UserTeamRepository,
    private val footballDataRepository: FootballDataRepository,
    private val currentUserId: Long
) : ViewModel() {

    private val _team = MutableStateFlow<UserTeam?>(null)
    private val _assignments = MutableStateFlow<Map<Int, Player>>(emptyMap())

    private val positionsFlow: Flow<List<FormationPosition>> = _team
        .map { it?.formationId }
        .distinctUntilChanged()
        .flatMapLatest { formationId ->
            if (formationId == null) flowOf(emptyList()) else repository.observePositionsForFormation(formationId)
        }

    val uiState: StateFlow<MyTeamUiState> = combine(
        _team,
        repository.observeFormations(),
        repository.observeTactics(),
        positionsFlow,
        _assignments,
        footballDataRepository.observePlayers()
    ) { flows ->
        @Suppress("UNCHECKED_CAST")
        MyTeamUiState(
            userTeam = flows[0] as UserTeam?,
            formations = flows[1] as List<TeamFormation>,
            tactics = flows[2] as List<Tactic>,
            positions = flows[3] as List<FormationPosition>,
            assignments = flows[4] as Map<Int, Player>,
            allPlayers = flows[5] as List<Player>
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MyTeamUiState())

    init {
        viewModelScope.launch {
            repository.seedIfEmpty()
            val team = repository.getOrCreateTeam(currentUserId, "My Dream Team")
            _team.value = team
        }
    }

    fun selectFormation(formationId: Long) {
        viewModelScope.launch {
            val team = _team.value ?: return@launch
            repository.updateFormationAndTactic(team, formationId, team.tacticId)
            _team.value = team.copy(formationId = formationId)
            _assignments.value = emptyMap()
        }
    }

    fun selectTactic(tacticId: Long) {
        viewModelScope.launch {
            val team = _team.value ?: return@launch
            repository.updateFormationAndTactic(team, team.formationId, tacticId)
            _team.value = team.copy(tacticId = tacticId)
        }
    }

    fun assignPlayer(slotNumber: Int, player: Player) {
        viewModelScope.launch {
            val team = _team.value ?: return@launch
            repository.assignPlayerToSlot(team.id, player.id, slotNumber)
            _assignments.value = _assignments.value.toMutableMap().apply { put(slotNumber, player) }
        }
    }

    fun submitTeam() {
        viewModelScope.launch {
            _team.value?.let {
                repository.submitTeam(it)
                _team.value = it.copy(submitted = true)
            }
        }
    }
}
