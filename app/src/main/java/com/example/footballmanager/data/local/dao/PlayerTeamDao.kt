package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.PlayerTeam
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerTeamDao {
    @Query("SELECT * FROM player_teams ORDER BY name ASC")
    fun observeAll(): Flow<List<PlayerTeam>>

    @Query("SELECT * FROM player_teams WHERE id = :id")
    suspend fun getById(id: Long): PlayerTeam?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(teams: List<PlayerTeam>)

    @Update
    suspend fun update(team: PlayerTeam)

    @Query("SELECT COUNT(*) FROM player_teams")
    suspend fun count(): Int

    @Query("UPDATE player_teams SET votes = votes + :delta WHERE id = :id")
    suspend fun addVotes(id: Long, delta: Int)
}
