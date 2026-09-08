package com.example.footballmanager.data.repository

import com.example.footballmanager.data.local.dao.*
import com.example.footballmanager.data.local.entities.*
import kotlinx.coroutines.flow.Flow

class UserTeamRepository(
    private val userTeamDao: UserTeamDao,
    private val userTeamPlayerDao: UserTeamPlayerDao,
    private val formationDao: TeamFormationDao,
    private val formationPositionDao: FormationPositionDao,
    private val tacticDao: TacticDao,
    private val userTeamVotingDao: UserTeamVotingDao
) {
    fun observeFormations(): Flow<List<TeamFormation>> = formationDao.observeAll()
    fun observeTactics(): Flow<List<Tactic>> = tacticDao.observeAll()
    fun observePositionsForFormation(formationId: Long): Flow<List<FormationPosition>> =
        formationPositionDao.observeForFormation(formationId)

    fun observeUserTeam(userId: Long): Flow<UserTeam?> = userTeamDao.observeByOwner(userId)
    fun observeTeamPlayers(userTeamId: Long): Flow<List<UserTeamPlayer>> = userTeamPlayerDao.observeForTeam(userTeamId)
    fun observeSubmittedTeams(): Flow<List<UserTeam>> = userTeamDao.observeSubmitted()

    suspend fun getOrCreateTeam(userId: Long, defaultName: String): UserTeam {
        return userTeamDao.findByOwner(userId) ?: run {
            val team = UserTeam(name = defaultName, formationId = null, tacticId = null, submitted = false, ownerUserId = userId)
            val id = userTeamDao.insert(team)
            team.copy(id = id)
        }
    }

    suspend fun updateFormationAndTactic(team: UserTeam, formationId: Long?, tacticId: Long?) {
        userTeamDao.update(team.copy(formationId = formationId, tacticId = tacticId))
    }

    suspend fun assignPlayerToSlot(userTeamId: Long, playerId: Long, slotNumber: Int) {
        userTeamPlayerDao.clearSlot(userTeamId, slotNumber)
        userTeamPlayerDao.insert(UserTeamPlayer(userTeamId = userTeamId, playerId = playerId, slotNumber = slotNumber))
    }

    suspend fun clearTeamPlayers(userTeamId: Long) {
        userTeamPlayerDao.clearTeam(userTeamId)
    }

    suspend fun submitTeam(team: UserTeam) {
        userTeamDao.update(team.copy(submitted = true))
    }

    suspend fun voteUserTeam(userTeamId: Long, userId: Long) {
        val existing = userTeamVotingDao.findVote(userTeamId, userId)
        if (existing == null) {
            userTeamVotingDao.insert(UserTeamVoting(userId = userId, userTeamId = userTeamId))
        }
    }

    suspend fun voteCount(userTeamId: Long): Int = userTeamVotingDao.countForTeam(userTeamId)

    /** Seeds a few formations/tactics on first run so the "build your team" screen isn't empty. */
    suspend fun seedIfEmpty() {
        if (formationDao.count() == 0) {
            val formations = listOf(
                TeamFormation(name = "4-4-2", description = "Balanced classic shape"),
                TeamFormation(name = "4-3-3", description = "Attacking wide wingers"),
                TeamFormation(name = "3-5-2", description = "Wing-backs, midfield heavy")
            )
            formationDao.insertAll(formations)

            val saved = formationDao.getAllOnce()
            val layouts = mapOf(
                "4-4-2" to listOf("GK", "DEF", "DEF", "DEF", "DEF", "MID", "MID", "MID", "MID", "FWD", "FWD"),
                "4-3-3" to listOf("GK", "DEF", "DEF", "DEF", "DEF", "MID", "MID", "MID", "FWD", "FWD", "FWD"),
                "3-5-2" to listOf("GK", "DEF", "DEF", "DEF", "MID", "MID", "MID", "MID", "MID", "FWD", "FWD")
            )
            saved.forEach { formation ->
                val layout = layouts[formation.name] ?: return@forEach
                val positions = layout.mapIndexed { index, pos ->
                    FormationPosition(formationId = formation.id, slotNumber = index + 1, position = pos)
                }
                formationPositionDao.insertAll(positions)
            }
        }
        if (tacticDao.count() == 0) {
            tacticDao.insertAll(
                listOf(
                    Tactic(details = "High press, quick transitions", style = TacticStyle.OFFENSIVE),
                    Tactic(details = "Deep block, counter attack", style = TacticStyle.DEFENSIVE),
                    Tactic(details = "Possession based, patient build-up", style = TacticStyle.BALANCED)
                )
            )
        }
    }
}
