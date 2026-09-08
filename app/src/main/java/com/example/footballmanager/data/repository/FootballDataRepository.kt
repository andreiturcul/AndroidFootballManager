package com.example.footballmanager.data.repository

import com.example.footballmanager.data.local.dao.PlayerDao
import com.example.footballmanager.data.local.dao.PlayerTeamDao
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.data.remote.ApiService
import kotlinx.coroutines.flow.Flow

/**
 * Talks to the remote API (2 HTTP endpoints), deserializes the JSON and stores
 * the result into the local Room database, which the UI then observes.
 */
class FootballDataRepository(
    private val api: ApiService,
    private val playerTeamDao: PlayerTeamDao,
    private val playerDao: PlayerDao
) {
    fun observeTeams(): Flow<List<PlayerTeam>> = playerTeamDao.observeAll()
    fun observePlayers(): Flow<List<Player>> = playerDao.observeAll()
    fun observePlayersForTeam(teamId: Long): Flow<List<Player>> = playerDao.observeByTeam(teamId)

    /** HTTP request #1: fetch all teams for a league and cache them in Room. */
    suspend fun refreshTeams(league: String): Result<Int> = runCatching {
        val response = api.searchTeams(league)
        val dtos = response.teams.orEmpty()
        val entities = dtos.mapNotNull { dto ->
            val name = dto.strTeam ?: return@mapNotNull null
            PlayerTeam(
                name = name,
                league = dto.strLeague,
                ucl = false,
                cup = false,
                badgeUrl = dto.strTeamBadge,
                remoteId = dto.idTeam
            )
        }
        if (entities.isNotEmpty()) playerTeamDao.insertAll(entities)
        entities.size
    }

    /** HTTP request #2: fetch the squad for a given remote team id and cache it in Room. */
    suspend fun refreshPlayers(remoteTeamId: String, localTeamId: Long): Result<Int> = runCatching {
        val response = api.lookupPlayers(remoteTeamId)
        val dtos = response.player.orEmpty()
        val entities = dtos.mapNotNull { dto ->
            val name = dto.strPlayer ?: return@mapNotNull null
            Player(
                name = name,
                position = dto.strPosition?.take(20) ?: "Unknown",
                teamId = localTeamId,
                price = (5..50).random().toDouble(), // API has no market value, simulate one
                remoteId = dto.idPlayer
            )
        }
        if (entities.isNotEmpty()) playerDao.insertAll(entities)
        entities.size
    }

    suspend fun addManualPlayer(player: Player) = playerDao.insert(player)

    suspend fun getPlayer(id: Long): Player? = playerDao.getById(id)
    suspend fun getTeam(id: Long): PlayerTeam? = playerTeamDao.getById(id)
}
