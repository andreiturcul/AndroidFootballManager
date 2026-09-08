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

    @Query("SELECT * FROM players")
    suspend fun getAllOnce(): List<Player>

    @Query("""
        DELETE FROM players 
        WHERE id NOT IN (
            SELECT MIN(id) 
            FROM players 
            GROUP BY COALESCE(NULLIF(remoteId, ''), name || '_' || COALESCE(teamId, 0))
        )
    """)
    suspend fun deleteDuplicates()

    @Query("""
        DELETE FROM players 
        WHERE LOWER(position) LIKE '%manager%' 
           OR LOWER(position) LIKE '%coach%' 
           OR LOWER(position) LIKE '%staff%' 
           OR LOWER(position) LIKE '%trainer%' 
           OR LOWER(position) LIKE '%physio%'
           OR LOWER(position) LIKE '%scout%'
           OR LOWER(position) LIKE '%analyst%'
           OR LOWER(position) LIKE '%assistant%'
           OR LOWER(name) LIKE '%manager%'
           OR LOWER(name) LIKE '%assistant%'
    """)
    suspend fun deleteNonPlayers()

    @Query("UPDATE players SET position = 'GK' WHERE LOWER(position) LIKE '%keeper%' OR LOWER(position) LIKE '%gk%'")
    suspend fun normalizeGk()

    @Query("UPDATE players SET position = 'DEF' WHERE LOWER(position) LIKE '%defender%' OR LOWER(position) LIKE '%back%' OR LOWER(position) LIKE '%cb%' OR LOWER(position) LIKE '%lb%' OR LOWER(position) LIKE '%rb%'")
    suspend fun normalizeDef()

    @Query("UPDATE players SET position = 'FWD' WHERE LOWER(position) LIKE '%forward%' OR LOWER(position) LIKE '%striker%' OR LOWER(position) LIKE '%winger%' OR LOWER(position) LIKE '%attack%' OR LOWER(position) LIKE '%cf%' OR LOWER(position) LIKE '%st%'")
    suspend fun normalizeFwd()

    @Query("UPDATE players SET position = 'MID' WHERE position NOT IN ('GK', 'DEF', 'MID', 'FWD')")
    suspend fun normalizeMid()
}
