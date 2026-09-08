package com.example.footballmanager.data.local

import androidx.room.TypeConverter
import com.example.footballmanager.data.local.entities.EventName
import com.example.footballmanager.data.local.entities.TacticStyle

class Converters {
    @TypeConverter
    fun fromTacticStyle(style: TacticStyle): String = style.name

    @TypeConverter
    fun toTacticStyle(value: String): TacticStyle = TacticStyle.valueOf(value)

    @TypeConverter
    fun fromEventName(name: EventName): String = name.name

    @TypeConverter
    fun toEventName(value: String): EventName = EventName.valueOf(value)
}
