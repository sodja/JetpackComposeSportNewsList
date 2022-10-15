package com.sodja.sportnews.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sodja.sportnews.domain.model.Sport


@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromSport(sport: Sport): String{
        return sport.name
    }
    @TypeConverter
    fun toSport(name: String): Sport{
        return Sport(name, name)
    }
}