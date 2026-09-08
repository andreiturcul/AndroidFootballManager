package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_team_votings")
data class UserTeamVoting(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val userTeamId: Long
)
