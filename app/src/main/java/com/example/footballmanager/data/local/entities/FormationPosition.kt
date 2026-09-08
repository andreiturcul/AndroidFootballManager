package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "formation_positions")
data class FormationPosition(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val formationId: Long,
    val slotNumber: Int,
    val position: String   // GK, DEF, MID, FWD
)
