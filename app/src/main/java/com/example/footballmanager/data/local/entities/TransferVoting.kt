package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transfer_votings")
data class TransferVoting(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val transferId: Long,
    val upVotes: Int = 0,
    val downVotes: Int = 0,
    val userId: Long,
    val userTeamId: Long?
)
