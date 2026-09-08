package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.FormationPosition
import com.example.footballmanager.data.local.entities.TeamFormation
import com.example.footballmanager.data.local.entities.Tactic
import com.example.footballmanager.data.local.entities.Event
import com.example.footballmanager.data.local.entities.Vote
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamFormationDao {
    @Query("SELECT * FROM team_formations ORDER BY name")
    fun observeAll(): Flow<List<TeamFormation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(formations: List<TeamFormation>)

    @Query("SELECT COUNT(*) FROM team_formations")
    suspend fun count(): Int

    @Query("SELECT * FROM team_formations ORDER BY id")
    suspend fun getAllOnce(): List<TeamFormation>
}

@Dao
interface FormationPositionDao {
    @Query("SELECT * FROM formation_positions WHERE formationId = :formationId ORDER BY slotNumber")
    fun observeForFormation(formationId: Long): Flow<List<FormationPosition>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(positions: List<FormationPosition>)
}

@Dao
interface TacticDao {
    @Query("SELECT * FROM tactics")
    fun observeAll(): Flow<List<Tactic>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(tactics: List<Tactic>)

    @Query("SELECT COUNT(*) FROM tactics")
    suspend fun count(): Int
}

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event): Long

    @Query("SELECT * FROM events WHERE name = :name LIMIT 1")
    suspend fun findByName(name: com.example.footballmanager.data.local.entities.EventName): Event?
}

@Dao
interface VoteDao {
    @Query("SELECT * FROM votes WHERE eventTypeId = :eventTypeId AND userId = :userId LIMIT 1")
    suspend fun findVote(eventTypeId: Long, userId: Long): Vote?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vote: Vote): Long
}
