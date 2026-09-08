package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.UserTeamPlayer
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTeamPlayerDao {
    @Query("SELECT * FROM user_team_players WHERE userTeamId = :userTeamId ORDER BY slotNumber")
    fun observeForTeam(userTeamId: Long): Flow<List<UserTeamPlayer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: UserTeamPlayer): Long

    @Query("SELECT * FROM user_team_players WHERE userTeamId = :userTeamId AND slotNumber = :slotNumber LIMIT 1")
    suspend fun findSlot(userTeamId: Long, slotNumber: Int): UserTeamPlayer?

    @Query("DELETE FROM user_team_players WHERE userTeamId = :userTeamId AND slotNumber = :slotNumber")
    suspend fun clearSlot(userTeamId: Long, slotNumber: Int)

    @Query("DELETE FROM user_team_players WHERE userTeamId = :userTeamId")
    suspend fun clearTeam(userTeamId: Long)
}
