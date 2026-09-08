package com.example.footballmanager.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "votes")
data class Vote(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val eventId: Long,
    val eventTypeId: Long,   // id of the transfer / team / proposal being voted on
    val userId: Long,
    val isUpvote: Boolean
)
