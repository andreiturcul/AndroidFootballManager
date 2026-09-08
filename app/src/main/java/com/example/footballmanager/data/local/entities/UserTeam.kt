package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_teams")
data class UserTeam(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val formationId: Long?,
    val tacticId: Long?,
    val submitted: Boolean = false,
    val ownerUserId: Long
)
