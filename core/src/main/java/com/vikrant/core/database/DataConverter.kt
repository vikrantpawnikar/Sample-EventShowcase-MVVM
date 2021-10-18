package com.vikrant.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vikrant.core.model.EventDetail


class DataConverter {
    @TypeConverter
    fun toEvent(value: String): List<EventDetail>{
        val gson = Gson()
        val type = object : TypeToken<List<EventDetail>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromEvent(value: List<EventDetail>): String {
        val gson = Gson()
        val type = object : TypeToken<List<EventDetail>>() {}.type
        return gson.toJson(value, type)
    }
}