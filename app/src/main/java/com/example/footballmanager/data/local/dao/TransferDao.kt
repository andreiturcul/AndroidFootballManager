package com.example.footballmanager.data.local.dao

import androidx.room.*
import com.example.footballmanager.data.local.entities.Transfer
import kotlinx.coroutines.flow.Flow

@Dao
interface TransferDao {
    @Query("SELECT * FROM transfers ORDER BY date DESC")
    fun observeAll(): Flow<List<Transfer>>

    @Insert
    suspend fun insert(transfer: Transfer): Long

    @Query("UPDATE transfers SET votes = votes + :delta WHERE id = :id")
    suspend fun addVotes(id: Long, delta: Int)
}
