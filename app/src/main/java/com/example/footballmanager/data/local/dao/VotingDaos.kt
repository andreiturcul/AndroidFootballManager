package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.UserTeamVoting
import com.example.footballmanager.data.local.entities.UserTransferProposal
import com.example.footballmanager.data.local.entities.UserTransferProposalVoting
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTeamVotingDao {
    @Query("SELECT * FROM user_team_votings WHERE userTeamId = :userTeamId AND userId = :userId LIMIT 1")
    suspend fun findVote(userTeamId: Long, userId: Long): UserTeamVoting?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vote: UserTeamVoting): Long

    @Query("SELECT COUNT(*) FROM user_team_votings WHERE userTeamId = :userTeamId")
    suspend fun countForTeam(userTeamId: Long): Int
}

@Dao
interface UserTransferProposalDao {
    @Query("SELECT * FROM user_transfer_proposals ORDER BY votes DESC")
    fun observeAll(): Flow<List<UserTransferProposal>>

    @Insert
    suspend fun insert(proposal: UserTransferProposal): Long

    @Query("SELECT COUNT(*) FROM user_transfer_proposals")
    suspend fun count(): Int

    @Query("UPDATE user_transfer_proposals SET votes = votes + :delta WHERE id = :id")
    suspend fun addVotes(id: Long, delta: Int)
}

@Dao
interface UserTransferProposalVotingDao {
    @Query("SELECT * FROM user_transfer_proposal_votings WHERE proposalId = :proposalId AND userId = :userId LIMIT 1")
    suspend fun findVote(proposalId: Long, userId: Long): UserTransferProposalVoting?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vote: UserTransferProposalVoting): Long
}
