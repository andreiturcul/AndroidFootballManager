package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.TransferVoting

@Dao
interface TransferVotingDao {
    @Query("SELECT * FROM transfer_votings WHERE transferId = :transferId AND userId = :userId LIMIT 1")
    suspend fun findVote(transferId: Long, userId: Long): TransferVoting?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vote: TransferVoting): Long

    @Update
    suspend fun update(vote: TransferVoting)
}
