package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_transfer_proposals")
data class UserTransferProposal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val playerId: Long,
    val fromTeamId: Long?,
    val toTeamId: Long?,
    val votes: Int = 0
)
