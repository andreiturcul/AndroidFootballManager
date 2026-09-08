package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transfers")
data class Transfer(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val playerId: Long,
    val fromTeamId: Long?,
    val toTeamId: Long?,
    val price: Double,
    val date: Long,       // epoch millis
    val votes: Int = 0
)
