package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.UserTeam
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTeamDao {
    @Query("SELECT * FROM user_teams WHERE ownerUserId = :userId LIMIT 1")
    suspend fun findByOwner(userId: Long): UserTeam?

    @Query("SELECT * FROM user_teams WHERE ownerUserId = :userId LIMIT 1")
    fun observeByOwner(userId: Long): Flow<UserTeam?>

    @Query("SELECT * FROM user_teams WHERE submitted = 1")
    fun observeSubmitted(): Flow<List<UserTeam>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: UserTeam): Long

    @Update
    suspend fun update(team: UserTeam)
}
