package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_teams")
data class PlayerTeam(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val ucl: Boolean = false,     // plays Champions League
    val league: String? = null,   // league name
    val cup: Boolean = false,     // plays domestic cup
    val votes: Int = 0,
    val badgeUrl: String? = null,
    val remoteId: String? = null
)
