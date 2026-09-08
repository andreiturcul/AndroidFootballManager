package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_team_players")
data class UserTeamPlayer(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userTeamId: Long,
    val playerId: Long,
    val slotNumber: Int
)
