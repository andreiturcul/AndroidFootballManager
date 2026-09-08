package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_transfer_proposal_votings")
data class UserTransferProposalVoting(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val proposalId: Long
)
