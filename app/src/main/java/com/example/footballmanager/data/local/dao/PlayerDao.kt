package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM players ORDER BY name ASC")
    fun observeAll(): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE teamId = :teamId ORDER BY position")
    fun observeByTeam(teamId: Long): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getById(id: Long): Player?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(players: List<Player>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(player: Player): Long

    @Query("SELECT COUNT(*) FROM players")
    suspend fun count(): Int
}
