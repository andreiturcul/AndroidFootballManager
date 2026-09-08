package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val nickname: String? = null,
    val position: String,        // GK, DEF, MID, FWD
    val teamId: Long? = null,    // FK -> PlayerTeam.id
    val price: Double = 0.0,
    val remoteId: String? = null // id coming from the external API, used to avoid duplicates
)
