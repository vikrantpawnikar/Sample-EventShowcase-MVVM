package com.vikrant.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vikrant.core.model.Events

@Database(entities = [Events::class], version = 2)
@TypeConverters(DataConverter::class)
abstract class EventDataBase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}