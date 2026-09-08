package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class EventName { TRANSFER, TRANSFER_PROPOSAL, USER_TEAM, PLAYER_TEAM }

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: EventName
)
