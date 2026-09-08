package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_formations")
data class TeamFormation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,       // e.g. "4-3-3"
    val description: String
)
