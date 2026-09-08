package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TacticStyle { OFFENSIVE, DEFENSIVE, BALANCED }

@Entity(tableName = "tactics")
data class Tactic(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val details: String,
    val style: TacticStyle
)
